package com.prilepskiy.domain.model

import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.EMPTY_STRING

data class NoteModel(
    val noteId: Long = DEFAULT_LONG,
    val pointId: Long = DEFAULT_LONG,
    val uri: String = EMPTY_STRING,
    val note: String = EMPTY_STRING,
)
