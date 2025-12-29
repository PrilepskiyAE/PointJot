package com.prilepskiy.presentation.addScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddPointScreen(point: Int?, onPopBack: () -> Unit,viewModel:  AddPointViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(modifier = Modifier.clickable(onClick = { onPopBack.invoke() }), text = "AddPointScreen $point")
    }
}