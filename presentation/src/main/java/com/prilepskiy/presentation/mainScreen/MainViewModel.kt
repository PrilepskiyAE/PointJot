package com.prilepskiy.presentation.mainScreen.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.common.MviBaseViewModel
import com.prilepskiy.common.Reducer
import com.prilepskiy.common.subscribe
import com.prilepskiy.presentation.mainScreen.MainAction
import com.prilepskiy.presentation.mainScreen.MainIntent
import com.prilepskiy.presentation.mainScreen.MainReducer
import com.prilepskiy.presentation.mainScreen.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainReducer: MainReducer, ) : MviBaseViewModel<MainState, MainAction, MainIntent>() {
    override var reducer: Reducer<MainAction, MainState> = mainReducer

    override fun initState(): MainState = MainState()

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnError -> onAction(MainAction.OnError(intent.error))
            is MainIntent.OnLoading -> onAction(MainAction.OnLoading(intent.isLoading))
            is MainIntent.OnClick -> {}

        }
    }


}