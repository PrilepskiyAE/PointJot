package com.prilepskiy.presentation.detailScreen

import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    detailReducer: DetailReducer,
) : MviBaseViewModel<DetailState, DetailAction, DetailIntent>() {
    override var reducer: Reducer<DetailAction, DetailState,> = detailReducer


    override fun initState(): DetailState = DetailState()
    override fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.Init -> {}
            is DetailIntent.OnError -> onAction(DetailAction.OnError(intent.error))
            is DetailIntent.OnLoading -> onAction(DetailAction.OnLoading(intent.isLoading))
        }
    }
}
