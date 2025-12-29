package com.prilepskiy.presentation.addScreen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Spaces
import com.prilepskiy.presentation.uiComponent.EmptyPhotoCardComponent
import com.prilepskiy.presentation.uiComponent.PhotoCardComponent
import com.prilepskiy.presentation.uiComponent.ToolbarStandardComponent
import com.prilepskiy.presentation.uiComponent.copyImageToPrivateStorage

@Composable
fun AddPointScreen(
    point: Int?,
    onPopBack: () -> Unit,
    viewModel: AddPointViewModel = hiltViewModel()
) {
    var selectedImageUri by remember { mutableStateOf<String?>(null) }
    AddPointScreen(
        point,
        onPopBack = onPopBack,
        selectedImageUri = selectedImageUri,
        saveUri = {
            selectedImageUri = it
        },
        saveOnClick = { onPopBack.invoke() })
}

@Composable
private fun AddPointScreen(
    pointId: Int?,
    selectedImageUri: String?,
    saveOnClick: () -> Unit,
    saveUri: (path: String?) -> Unit,
    onPopBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri -> if (uri != null) saveImageAndUpdateDb(context, uri,saveUri=saveUri) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            galleryLauncher.launch("image/*")
        } else {
            // Пользователь отказал
            Toast.makeText(
                context,
                "Разрешение на доступ к галерее не получено",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun openGallery() {
        val permission = if (Build.VERSION.SDK_INT >= 33) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            galleryLauncher.launch("image/*")
        } else {
            permissionLauncher.launch(permission)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue600)
            .verticalScroll(scrollState),
    ) {
        ToolbarStandardComponent(
            modifier = Modifier.padding(vertical = Spaces.space10),
            title = if (pointId == 0 || pointId == null) "Создать цель" else "Редоктировать цель",
            onBackPressed = onPopBack,
            textColor = Gray80,
            iconColor = Gray80,
            firstIcon = Icons.Default.ArrowBack,
            secondIcon = Icons.Default.Check,
            onSecondClick = saveOnClick
        )
        if (selectedImageUri != null) {
            PhotoCardComponent(modifier = Modifier.align(Alignment.CenterHorizontally), context = context, path = selectedImageUri){

            }
        } else {
            EmptyPhotoCardComponent(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                openGallery()
            }
        }
    }
}
private fun saveImageAndUpdateDb(context: Context, uri: Uri, saveUri: (path: String?) -> Unit,) {
    val localPath = copyImageToPrivateStorage(context, uri)
    if (localPath != null) {
        saveUri(localPath)
    }
}