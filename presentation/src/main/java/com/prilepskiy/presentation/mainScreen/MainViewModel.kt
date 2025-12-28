package com.prilepskiy.presentation.mainScreen

import androidx.lifecycle.viewModelScope
import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import com.prilepskiy.common.subscribe
import com.prilepskiy.domain.categoryUseCase.AddCategoryUseCase
import com.prilepskiy.domain.categoryUseCase.DeleteCategoryUseCase
import com.prilepskiy.domain.categoryUseCase.GetAllCategoryUseCase
import com.prilepskiy.domain.model.CategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainReducer: MainReducer,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val addCategoryUseCase: AddCategoryUseCase
) : MviBaseViewModel<MainState, MainAction, MainIntent>() {
    override var reducer: Reducer<MainAction, MainState> = mainReducer


    override fun initState(): MainState = MainState()

    init {
        getAllCategoryUseCase.invoke().subscribe(scope=viewModelScope, onStart = {
            onAction(MainAction.OnLoading(true))
        },success={ list->
            onAction(MainAction.OnLoading(false))
            if (list.isEmpty()) {
                val first= CategoryModel(categoryId = 1, "Все", isActive = true)
                addCategoryUseCase.invoke(first)
                onAction(MainAction.GetCategory(listOf(first)))
            }else{
                onAction(MainAction.GetCategory(list))
            }

        }, error = {
            onAction(MainAction.OnError("Ой что-то пошло не так"))
        })


    }

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnError -> onAction(MainAction.OnError(intent.error))
            is MainIntent.OnLoading -> onAction(MainAction.OnLoading(intent.isLoading))
            is MainIntent.OnClickPoint -> {}

        }
    }


}