package com.prilepskiy.presentation.detailScreen

import com.prilepskiy.common.Reducer
import javax.inject.Inject


class DetailReducer @Inject constructor() : Reducer<DetailAction, DetailState> {
    override fun reduce(action: DetailAction, state: DetailState): DetailState = when (action) {
        is DetailAction.Init -> state
        is DetailAction.OnError -> state.copy(error = action.error, isLoading = false)
        is DetailAction.OnLoading ->  state.copy(isLoading = action.isLoading)
    }

}