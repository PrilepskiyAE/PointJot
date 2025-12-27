package com.prilepskiy.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.EMPTY_STRING
@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long = DEFAULT_LONG,
    val pointId: Long = DEFAULT_LONG,
    val uri: String = EMPTY_STRING,
    val note: String = EMPTY_STRING,
    )