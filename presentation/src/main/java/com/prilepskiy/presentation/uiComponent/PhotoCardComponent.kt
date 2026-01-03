package com.prilepskiy.presentation.uiComponent

import android.content.Context
import android.media.Image
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.ImageResult
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Sizes
import com.prilepskiy.common.Spaces
import com.prilepskiy.presentation.R
import com.prilepskiy.presentation.simpleClickable
import coil3.request.ImageRequest.Builder
import coil3.request.allowHardware
import coil3.request.crossfade

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
fun PhotoCardComponent(
    modifier: Modifier = Modifier,
    path: String,
    size: Dp =Spaces.space300,
    onClick: () -> Unit,
) {
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.size(size)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(path)
                .crossfade(true)
                .allowHardware(false)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.DISABLED)
                .build(),
            contentDescription = "Photo",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .clickable(onClick = onClick),
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = R.drawable.ic_sync_error),
            error = painterResource(id = R.drawable.ic_foto_error),
            onSuccess = {
                isLoading = false
                errorMessage = null
            },
            onError = { error ->
                isLoading = false
                errorMessage = error.result.throwable.localizedMessage
            }
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
