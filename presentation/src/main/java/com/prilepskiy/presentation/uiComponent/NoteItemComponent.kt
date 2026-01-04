package com.prilepskiy.presentation.uiComponent

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.prilepskiy.common.BodyTextStyles
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Green600
import com.prilepskiy.common.Spaces
import com.prilepskiy.domain.model.NoteModel
import com.prilepskiy.presentation.simpleClickable
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NoteItemComponent(
    pos:Int,
    noteModel: NoteModel,
    openNote: (noteModel: NoteModel) -> Unit,
    onDeleteNote: (noteModel: NoteModel) -> Unit,
) {
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f).simpleClickable() {
                openNote.invoke(noteModel)
            }) {
                Text(
                    modifier = Modifier.padding(horizontal = Spaces.space16),
                    text = "Запись №${(pos+1)}",
                    style = BodyTextStyles.Large,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 8,
                    color = Green600
                )
                Text(
                    modifier = Modifier.padding(horizontal = Spaces.space16),
                    text = noteModel.note,
                    style = BodyTextStyles.Large,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 8,
                    color = Gray80
                )

                Text(
                    modifier = Modifier.padding(horizontal = Spaces.space16),
                    text = dateFormatTime(noteModel.date),
                    style = BodyTextStyles.Small,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 8,
                    color = Gray80
                )

            }
            IconButton(onClick = { onDeleteNote.invoke(noteModel) }) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Gray80,
                    modifier = Modifier.size(Spaces.space24)
                )
            }
        }
        Surface(
            color = Gray80,
            shape = RoundedCornerShape(Spaces.space8),
            modifier = Modifier
                .height(Spaces.space1)
                .fillMaxWidth()
        ) {}
    }
}
fun dateFormatTime(date: Long): String {
    return try {
        val formatter = SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault())
        val dateCorrect = if (date == 0L) System.currentTimeMillis() else date
        formatter.format(dateCorrect)
    } catch (e: Exception) {
        Log.d("TAG_ERROR", "dateFormat: $e")
        EMPTY_STRING
    }
}