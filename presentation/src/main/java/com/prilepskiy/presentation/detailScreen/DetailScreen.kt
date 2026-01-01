package com.prilepskiy.presentation.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.prilepskiy.common.Black800
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.Gray90
import com.prilepskiy.common.Sizes
import com.prilepskiy.common.Spaces
import com.prilepskiy.presentation.R

@Composable
fun DetailScreen(point: Long?, onPopBack: () -> Unit, onUpdatePoint: (Long?) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(Blue600),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable(onClick = { onPopBack.invoke() }),
            text = "DetailScreen $point"
        )
        Button(
            onClick = { onUpdatePoint.invoke(point) },
            colors = ButtonDefaults.buttonColors(containerColor = Gray90, contentColor = Black800),
            contentPadding = PaddingValues(horizontal = Spaces.space16, vertical = Spaces.space9)
        ) {
            Text(
                text = stringResource(R.string.update_point),
                color = Color.White,
                fontSize = Sizes.size18
            )
        }
    }
}