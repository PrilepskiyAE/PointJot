package com.prilepskiy.presentation.uiComponent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTargetMarker
import com.prilepskiy.domain.model.NoteModel
import com.prilepskiy.domain.model.StageModel

@Composable
fun NoteItemComponent(noteModel: NoteModel,
                      openNote: (noteModel: NoteModel) -> Unit,
                      onDeleteNote: (noteModel: NoteModel) -> Unit,) {
}