package com.prilepskiy.presentation.uiComponent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextComponent(modifier: Modifier, text:String){
    Text(
        modifier = modifier, text = text)
}