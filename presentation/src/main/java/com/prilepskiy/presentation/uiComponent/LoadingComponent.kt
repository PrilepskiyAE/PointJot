package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.prilepskiy.common.Blue600
import com.prilepskiy.presentation.R


@Composable
fun LoadingComponent() {
    Box(modifier = Modifier.fillMaxSize().background(Blue600), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextComponent(modifier = Modifier, text = stringResource(R.string.loading))
            LinearProgressIndicator()
        }
    }
}