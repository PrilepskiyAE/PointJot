package com.prilepskiy.presentation.uiComponent

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Sizes
import com.prilepskiy.common.Spaces
import com.prilepskiy.presentation.R
import com.prilepskiy.presentation.simpleClickable

@Composable
fun EmptyPhotoCardComponent(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .simpleClickable(onClick = onClick)
            .size(Spaces.space200)
            .padding(Spaces.space16),
        shape = RoundedCornerShape(Spaces.space16),
        colors = CardDefaults.cardColors().copy(containerColor = Blue600,),
        border = BorderStroke(Spaces.space2,Gray80),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_foto),
                contentDescription = null,
                modifier = Modifier.size(Spaces.space24),
                tint = Gray80
            )
            Text(
                text = stringResource(R.string.add_photo),
                fontSize = Sizes.size14,
                color = Gray80
            )
        }
    }
}

@Composable
fun PhotoCardComponent(modifier: Modifier = Modifier, path: String, onClick: () -> Unit) {

    AsyncImage(
        modifier = modifier
            .simpleClickable(onClick = onClick)
            .size(Spaces.space300),
        model = path,
        contentDescription = EMPTY_STRING,
        onError = {
            Log.d("TAG", "PhotoCardComponent: ${it.result}")
        },
        contentScale = ContentScale.Fit,
        error = painterResource(R.drawable.ic_foto_error)
    )
}
