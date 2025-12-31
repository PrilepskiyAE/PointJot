package com.prilepskiy.presentation.uiComponent

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.prilepskiy.common.Blue600
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.LabelTextStyles
import com.prilepskiy.common.Spaces
import com.prilepskiy.presentation.R
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(
    modifier: Modifier,
    selectedDate: Long,
    onValueChange: (Long) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateText by remember { mutableStateOf(dateFormat(selectedDate)) }

    val initialDate = if (selectedDate > 0) selectedDate else System.currentTimeMillis()
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate
    )

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = Spaces.space16),
            text = stringResource(R.string.date_close),
            style = LabelTextStyles.Small,
            color = Gray80
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spaces.space60)
                .padding(vertical = Spaces.space2, horizontal = Spaces.space16),
            shape = RoundedCornerShape(Spaces.space4),
            colors = ButtonDefaults.buttonColors().copy(containerColor = Blue600),
            border = BorderStroke(Spaces.space1, Gray80),
            onClick = { showDatePicker = true }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = selectedDateText
            )
        }

    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val selectedMillis = datePickerState.selectedDateMillis
                    if (selectedMillis != null) {
                        onValueChange(selectedMillis)
                        selectedDateText = dateFormat(selectedMillis)
                    }
                    showDatePicker = false
                }) {
                    Text(stringResource(R.string.save))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.cansel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}


fun dateFormat(date: Long): String {
    return try {
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val dateCorrect = if (date == 0L) System.currentTimeMillis() else date
        formatter.format(dateCorrect)
    } catch (e: Exception) {
        Log.d("TAG_ERROR", "dateFormat: $e")
        EMPTY_STRING
    }
}
