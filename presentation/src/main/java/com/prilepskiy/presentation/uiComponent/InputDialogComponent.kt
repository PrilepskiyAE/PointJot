package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.MAX_LEN_CATEGORY
import com.prilepskiy.presentation.R

@Composable
fun InputDialogComponent(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    title: String,
    confirmText: String,
    placeholder: String
) {
    var inputText by remember { mutableStateOf(EMPTY_STRING) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = inputText,
                onValueChange = {
                    if (inputText.length < MAX_LEN_CATEGORY) {
                        inputText = it
                    }
                },
                maxLines = 1,
                singleLine = true,
                placeholder = { Text(placeholder) },

                )
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(inputText)
                    onDismiss()
                },
                enabled = inputText.isNotBlank()
            ) {
                Text(confirmText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cansel))
            }
        }
    )
}