package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.prilepskiy.common.Gray100
import com.prilepskiy.common.Gray200
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Gray90
import com.prilepskiy.common.Green200
import com.prilepskiy.common.LabelTextStyles
import com.prilepskiy.common.MAX_LEN_INPUT_BASIC
import com.prilepskiy.common.Red500
import com.prilepskiy.common.Spaces

@Composable
fun InputFieldComponent(
    modifier: Modifier = Modifier,
    label: String,
    textValue: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(
                horizontal = Spaces.space16
            ), text = label,
            style = LabelTextStyles.Small,
            color = Gray80

        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Spaces.space2, horizontal = Spaces.space16),
            value = textValue,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            maxLines = 4,
            isError = textValue.length > MAX_LEN_INPUT_BASIC,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Gray90,
                unfocusedTextColor = Gray90,
                focusedBorderColor = Green200,
                unfocusedBorderColor = Gray100,
                focusedLabelColor = Gray90,
                unfocusedLabelColor = Gray80,
                cursorColor = Gray90,
                focusedPlaceholderColor = Gray80,
                unfocusedPlaceholderColor = Gray80,
                errorBorderColor = Red500,
                errorLabelColor = Red500
            )
        )
    }
}