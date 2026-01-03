package com.prilepskiy.presentation.uiComponent

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.BodyTextStyles
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Green500
import com.prilepskiy.common.Red500
import com.prilepskiy.common.Spaces
import com.prilepskiy.common.saveImageAndUpdateDb
import com.prilepskiy.domain.model.NoteModel
import com.prilepskiy.presentation.R

@Composable
fun AddNoteDialogComponent(
    noteModel: NoteModel,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Создание заметки") },
        shape = RoundedCornerShape(Spaces.space16),
        text = {
            val context = LocalContext.current
            var selectedImageUri by remember { mutableStateOf(noteModel.uri) }
            var valueDescription by remember { mutableStateOf(noteModel.uri) }
            val galleryLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            )
            { uri ->
                if (uri != null) saveImageAndUpdateDb(context, uri, saveUri = {
                    selectedImageUri = it ?: EMPTY_STRING
                })
            }

            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            )
            { isGranted ->
                if (isGranted) {
                    galleryLauncher.launch("image/*")
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.permisshen_message),
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
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                if (selectedImageUri.isNotEmpty()) {
                    PhotoCardComponent(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        path = selectedImageUri
                    ) {
                        openGallery()
                    }
                } else {
                    EmptyPhotoCardComponent(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        openGallery()
                    }
                }

                InputFieldComponent(
                    modifier = Modifier.padding(vertical = Spaces.space6),
                    label = stringResource(R.string.input5),
                    textValue = valueDescription,
                    placeholder = "Описание",
                    onValueChange = {
                        valueDescription = it
                    },
                )


            }
        },
        containerColor= Blue600,
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Green500)
            ) {
                Text(stringResource(R.string.save), color = Gray80)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cansel), color = Gray80)
            }
        }
    )
}