package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.prilepskiy.presentation.R


@Composable
fun LoadingComponent() {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextComponent(modifier = Modifier, text = stringResource(R.string.loading))
            LinearProgressIndicator()
        }
    }
}