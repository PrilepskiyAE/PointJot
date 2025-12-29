package com.prilepskiy.presentation.addScreen

import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import com.prilepskiy.domain.categoryUseCase.GetAllCategoryUseCase
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
            is AddPointIntent.OnError -> onAction(AddPointAction.OnError(intent.error))
            is AddPointIntent.OnLoading -> onAction(AddPointAction.OnLoading(intent.isLoading))
        }
    }
}