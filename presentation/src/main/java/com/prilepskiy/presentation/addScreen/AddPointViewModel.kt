package com.prilepskiy.presentation.addScreen

import androidx.lifecycle.viewModelScope
import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.ID_SECOND_CATEGORY
import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import com.prilepskiy.common.subscribe
import com.prilepskiy.domain.categoryUseCase.GetAllCategoryUseCase
import com.prilepskiy.domain.model.PointModel
import com.prilepskiy.domain.pointUseCase.AddPointUseCase
import com.prilepskiy.domain.pointUseCase.GetPointUseCase
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangeCategory
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangeDate
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangeMotivation
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangePhotoPatch
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangePointName
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangeReward
import com.prilepskiy.presentation.addScreen.AddPointAction.OnError
import com.prilepskiy.presentation.addScreen.AddPointAction.OnLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPointViewModel @Inject constructor(
    private val addPointReducer: AddPointReducer,
    getAllCategoryUseCase: GetAllCategoryUseCase,
    private val addPointUseCase: AddPointUseCase,
    private val getPointUseCase: GetPointUseCase
) : MviBaseViewModel<AddPointState, AddPointAction, AddPointIntent>() {
    override var reducer: Reducer<AddPointAction, AddPointState> = addPointReducer
    override fun initState(): AddPointState = AddPointState()

    init {
        getAllCategoryUseCase.invoke()
            .subscribe(scope = viewModelScope, onStart = {
                onAction(OnLoading(true))
            }, success = { list ->
                onAction(AddPointAction.InitCategory(list))
            }, error = {
                onAction(OnError("Ой что-то пошло не так"))
            })
    }

    override fun handleIntent(intent: AddPointIntent) {
        when (intent) {
            is AddPointIntent.OnError -> onAction(OnError(intent.error))
            is AddPointIntent.OnLoading -> onAction(OnLoading(intent.isLoading))
            is AddPointIntent.ChangePhotoPatch -> onAction(ChangePhotoPatch(intent.patch))
            is AddPointIntent.ChangeDate -> onAction(ChangeDate(intent.value))
            is AddPointIntent.ChangeMotivation -> onAction(ChangeMotivation(intent.value))
            is AddPointIntent.ChangePointName -> onAction(ChangePointName(intent.value))
            is AddPointIntent.ChangeReward -> onAction(ChangeReward(intent.value))
            is AddPointIntent.ChangeCategory -> onAction(ChangeCategory(intent.value))
            is AddPointIntent.OnClickSave -> {

                if (intent.id == DEFAULT_LONG) {
                    viewModelScope.launch {
                        addPointUseCase.invoke(
                            PointModel(
                                pointId = intent.id.toLong(),
                                categoryId = viewState.selectedCategory?.categoryId
                                    ?: ID_SECOND_CATEGORY,
                                uri = viewState.selectedImageUri ?: EMPTY_STRING,
                                pointName = viewState.pointName,
                                motivation = viewState.motivation,
                                reward = viewState.reward,
                                date = viewState.date,
                                isActive = true,
                                fullNote = 0,
                                colFinished = 0
                            )
                        )
                    }
                } else {
                    intent.id?.let { id ->
                        getPointUseCase.invoke(id).subscribe(scope = viewModelScope, onStart = {
                            onAction(OnLoading(true))
                        }, success = { list ->
                            list.firstOrNull()?.let { item ->
                                addPointUseCase.invoke(
                                    PointModel(
                                        pointId = intent.id,
                                        categoryId = viewState.selectedCategory?.categoryId
                                            ?: ID_SECOND_CATEGORY,
                                        uri = viewState.selectedImageUri ?: EMPTY_STRING,
                                        pointName = viewState.pointName,
                                        motivation = viewState.motivation,
                                        reward = viewState.reward,
                                        date = viewState.date,
                                        isActive = item.isActive,
                                        fullNote = item.fullNote,
                                        colFinished = item.colFinished
                                    )
                                )
                            }
                        }, error = {
                            onAction(OnError("Ой что-то пошло не так"))
                        })
                    }
                }
            }

            is AddPointIntent.InitPoint -> {
                intent.id?.let { getPointUseCase.invoke(it) }
                    ?.subscribe(scope = viewModelScope, onStart = {
                        onAction(OnLoading(true))
                    }, success = { list ->
                        list.firstOrNull()?.let { item ->
                            onAction(
                                AddPointAction.InitPoint(
                                    pointId = item.pointId,
                                    pointName = item.pointName,
                                    motivation = item.motivation,
                                    reward = item.reward,
                                    selectedImageUri = item.uri,
                                    selectedCategoryId = item.categoryId,
                                    date = if (item.date == 0L) System.currentTimeMillis() else item.date
                                )
                            )
                        }
                    }, error = {
                        onAction(OnError("Ой что-то пошло не так"))
                    })

            }

        }
    }
}
