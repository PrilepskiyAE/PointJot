package com.prilepskiy.presentation.mainScreen

import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainReducer: MainReducer, ) : MviBaseViewModel<MainState, MainAction, MainIntent>() {
    override var reducer: Reducer<MainAction, MainState> = mainReducer


    override fun initState(): MainState = MainState()

    init {

    }

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnError -> onAction(MainAction.OnError(intent.error))
            is MainIntent.OnLoading -> onAction(MainAction.OnLoading(intent.isLoading))
            is MainIntent.OnClick -> {}

        }
    }


}