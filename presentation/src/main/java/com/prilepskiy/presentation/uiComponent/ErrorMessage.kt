package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.prilepskiy.presentation.R

@Composable
fun ErrorMessageComponent(textError: String, onClickClear: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextComponent(modifier = Modifier, text = textError)
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp), onClick = onClickClear
            ) {
                Text(stringResource(R.string.button_repite))
            }
        }
    }
}