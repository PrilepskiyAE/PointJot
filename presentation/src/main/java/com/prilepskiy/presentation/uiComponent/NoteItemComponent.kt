package com.prilepskiy.presentation.uiComponent

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
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Spaces
import com.prilepskiy.domain.model.NoteModel
import com.prilepskiy.presentation.simpleClickable

@Composable
fun NoteItemComponent(
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
                    text = noteModel.note,
                    style = BodyTextStyles.Medium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
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