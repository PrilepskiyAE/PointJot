package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.BodyTextStyles
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Green500
import com.prilepskiy.common.Red500
import com.prilepskiy.common.Spaces
import com.prilepskiy.presentation.R

@Composable
fun AddStageDialogComponent(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Создание этапа") },
        text = {
            var valueName by remember { mutableStateOf(EMPTY_STRING) }
            var valueDescription by remember { mutableStateOf(EMPTY_STRING) }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                InputFieldComponent(
                    modifier = Modifier.padding(vertical = Spaces.space6),
                    label = stringResource(R.string.input1),
                    textValue = valueName,
                    placeholder = "Название этапа",
                    onValueChange = {
                        valueName=it
                    },
                )

                InputFieldComponent(
                    modifier = Modifier.padding(vertical = Spaces.space6),
                    label = stringResource(R.string.input5),
                    textValue = valueDescription,
                    placeholder = "Описание",
                    onValueChange = {
                        valueDescription=it
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