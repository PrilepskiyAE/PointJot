package com.prilepskiy.presentation.addScreen

import com.prilepskiy.common.Reducer
import javax.inject.Inject

class AddPointReducer @Inject constructor() : Reducer<AddPointAction, AddPointState> {
    override fun reduce(action: AddPointAction, state: AddPointState): AddPointState = when (action) {
        is AddPointAction.OnError -> state.copy(error = action.error, isLoading = false)
        is AddPointAction.OnLoading ->  state.copy(isLoading = action.isLoading)
    }

}