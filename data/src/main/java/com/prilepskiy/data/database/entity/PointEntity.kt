package com.prilepskiy.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.EMPTY_STRING

@Entity
data class PointEntity(
    @PrimaryKey(autoGenerate = true)
    val pointId: Long = DEFAULT_LONG,
    val categoryId: Long = DEFAULT_LONG,
    val pointName: String,
    val uri: String = EMPTY_STRING,
    val motivation: String = EMPTY_STRING,
    val reward: String = EMPTY_STRING,
    val date: Long = DEFAULT_LONG,
    val isActive: Boolean = true,
    val fullNote: Long = DEFAULT_LONG,
    val colFinished: Long = DEFAULT_LONG,
)
