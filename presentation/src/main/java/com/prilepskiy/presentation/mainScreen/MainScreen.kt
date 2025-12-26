package com.prilepskiy.presentation.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.presentation.R
import com.prilepskiy.presentation.mainScreen.viewModel.MainViewModel
import com.prilepskiy.presentation.uiComponent.ErrorMessageComponent
import com.prilepskiy.presentation.uiComponent.LoadingComponent

@Composable
fun MainScreen(goToPoint: (Int) -> Unit,viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.viewState

    if (state.isLoading) {
        LoadingComponent()
    } else if (!state.error.isNullOrEmpty()) {
        ErrorMessageComponent(textError = state.error) {

        }
    } else {
       MainScreen(
            state = state,
           goToPoint=goToPoint
        )
    }
}

@Composable
private fun MainScreen(state: MainState, goToPoint: (Int) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(modifier = Modifier.clickable(onClick = { goToPoint.invoke(99) }), text = "MainScreen")
    }
}
