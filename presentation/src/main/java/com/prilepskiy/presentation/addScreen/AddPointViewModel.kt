package com.prilepskiy.presentation.addScreen

import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import com.prilepskiy.domain.categoryUseCase.GetAllCategoryUseCase
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangeDate
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangeMotivation
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangePhotoPatch
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangePointName
import com.prilepskiy.presentation.addScreen.AddPointAction.ChangeReward
import com.prilepskiy.presentation.addScreen.AddPointAction.OnError
import com.prilepskiy.presentation.addScreen.AddPointAction.OnLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPointViewModel @Inject constructor(
    private val addPointReducer: AddPointReducer,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
) : MviBaseViewModel<AddPointState, AddPointAction, AddPointIntent>() {
    override var reducer: Reducer<AddPointAction, AddPointState> = addPointReducer

    override fun initState(): AddPointState = AddPointState()

    override fun handleIntent(intent: AddPointIntent) {
        when (intent) {
            is AddPointIntent.OnError -> onAction(OnError(intent.error))
            is AddPointIntent.OnLoading -> onAction(OnLoading(intent.isLoading))
            is AddPointIntent.ChangePhotoPatch -> onAction(ChangePhotoPatch(intent.patch))
            is AddPointIntent.ChangeDate -> onAction(ChangeDate(intent.value))
            is AddPointIntent.ChangeMotivation -> onAction(ChangeMotivation(intent.value))
            is AddPointIntent.ChangePointName -> onAction(ChangePointName(intent.value))
            is AddPointIntent.ChangeReward -> onAction(ChangeReward(intent.value))
        }
    }
}