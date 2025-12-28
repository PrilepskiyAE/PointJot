package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.stringResource
import com.prilepskiy.common.BodyTextStyles
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Red500
import com.prilepskiy.presentation.R

@Composable
fun DeleteCategoryDialogComponent(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    categoryName: String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.delete_category)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.dialiog_del_category_label1, categoryName),
                    style = BodyTextStyles.Medium,
                )
                Text(
                    text = stringResource(R.string.dialiog_del_category_label2),
                     style = BodyTextStyles.Large,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Red500)
            ) {
                Text(stringResource(R.string.del), color = Gray80)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cansel))
            }
        }
    )
}