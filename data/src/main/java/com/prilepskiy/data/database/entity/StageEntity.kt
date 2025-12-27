package com.prilepskiy.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.EMPTY_STRING
@Entity
data class StageEntity(
    @PrimaryKey(autoGenerate = true)
    val stageId: Long = DEFAULT_LONG,
    val pointId: Long = DEFAULT_LONG,
    val title: String = EMPTY_STRING,
    val label: String = EMPTY_STRING,
    val isActive: Boolean = true,
)
