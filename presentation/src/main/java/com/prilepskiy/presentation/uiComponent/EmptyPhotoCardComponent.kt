package com.prilepskiy.presentation.uiComponent

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Sizes
import com.prilepskiy.common.Spaces
import com.prilepskiy.presentation.R
import com.prilepskiy.presentation.simpleClickable
import java.io.File
import java.io.InputStream

@Composable
fun EmptyPhotoCardComponent(modifier: Modifier = Modifier,onClick: () -> Unit,) {
    Card(
        modifier = modifier
            .simpleClickable(onClick = onClick)
            .size(Spaces.space200)
            .padding(Spaces.space16),
        shape = RoundedCornerShape(Spaces.space16),
        colors = CardDefaults.cardColors().copy(containerColor = Blue600),
        border = BorderStroke(Spaces.space2, Color.Black),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter= painterResource(R.drawable.ic_foto),
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
fun PhotoCardComponent(modifier: Modifier = Modifier, context: Context, path: String, onClick: () -> Unit,) {

        AsyncImage(
            modifier = modifier
                .simpleClickable(onClick = onClick)
                .size(Spaces.space300),
            model = path,
            contentDescription = EMPTY_STRING,
            onError={
                Log.d("TAG", "PhotoCardComponent: ${it.result}")
            },
            contentScale = ContentScale.Fit,
            error = painterResource(R.drawable.ic_foto_error)
        )
    }



fun getImageUri(context: Context, file: String): Uri? {
    Log.d("TAG999", "getImageUri: $file")
    return try {
        val file = File(file)
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    } catch (e: IllegalArgumentException) {
        null
    }
}

fun copyImageToPrivateStorage(
    context: Context,
    sourceUri: Uri
): String? {
    return try {

        val imagesDir = File(context.filesDir, "images")
        if (!imagesDir.exists()) {
            imagesDir.mkdirs()
        }

        val fileName = "img_${System.currentTimeMillis()}.jpg"
        val destFile = File(imagesDir, fileName)

        context.contentResolver.openInputStream(sourceUri)?.use { input ->
            destFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        destFile.absolutePath
    } catch (e: Exception) {
        Log.e("CopyImage", "Failed to copy image", e)
        null
    }
}