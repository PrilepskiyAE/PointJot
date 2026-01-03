package com.prilepskiy.presentation.detailScreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import com.prilepskiy.common.deleteImageFromPrivateStorage
import com.prilepskiy.common.subscribe
import com.prilepskiy.domain.categoryUseCase.GetAllCategoryUseCase
import com.prilepskiy.domain.pointUseCase.AddPointUseCase
import com.prilepskiy.domain.pointUseCase.DeletePointUseCase
import com.prilepskiy.domain.pointUseCase.GetPointUseCase
import com.prilepskiy.presentation.addScreen.AddPointViewModel
import com.prilepskiy.presentation.detailScreen.DetailAction.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(
    detailReducer: DetailReducer,
    private val getPoint: GetPointUseCase,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val deletePointUseCase: DeletePointUseCase,
    private val addPointUseCase: AddPointUseCase,
) : MviBaseViewModel<DetailState, DetailAction, DetailIntent>() {
    override var reducer: Reducer<DetailAction, DetailState,> = detailReducer


    override fun initState(): DetailState = DetailState()
    override fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.Init -> {

                getAllCategoryUseCase.invoke().subscribe(viewModelScope, success = {
                    onAction( GetCategory(it))
                })

                getPoint.invoke(intent.pointId).subscribe(
                    viewModelScope, onStart = {
                        onAction(OnLoading(true))
                    }
                    , success = { result ->
                        result.firstOrNull()?.let { point->
                            onAction(Init(point))
                        }


                                }, error = { OnError(it.localizedMessage) }
                )
            }
            is DetailIntent.OnError -> onAction(OnError(intent.error))
            is DetailIntent.OnLoading -> onAction(OnLoading(intent.isLoading))
            is DetailIntent.OnClickSuccess -> {
                viewModelScope.launch {
                    val isActive=viewState.point?.isActive?:true
                    val point = viewState.point?.copy(isActive = !isActive)
                    point?.let {
                        addPointUseCase.invoke(it)
                        intent.onSuccess.invoke()
                    }
                }
            }
            is DetailIntent.OnClickDelete ->{
                viewModelScope.launch {
                    viewState.point?.let {
                        deletePointUseCase.invoke(it.pointId)
                       val test = deleteImageFromPrivateStorage(it.uri)
                        intent.onDelete.invoke()
                    }
                }
            }
        }
    }
}
