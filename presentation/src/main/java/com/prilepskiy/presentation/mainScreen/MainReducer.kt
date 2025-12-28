package com.prilepskiy.presentation.mainScreen

import com.prilepskiy.common.Reducer
import javax.inject.Inject

class MainReducer @Inject constructor() : Reducer<MainAction, MainState> {
    override fun reduce(action: MainAction, state: MainState): MainState = when (action) {
        is MainAction.OnLoading-> state.copy(isLoading = action.isLoading)
        is MainAction.OnError -> state.copy(error = action.error, isLoading = false)
        is MainAction.GetCategory -> state.copy(categoryList = action.categoryList)

    }
}