package com.prilepskiy.domain.model

import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.EMPTY_STRING

data class PointModel(
    val pointId: Long = DEFAULT_LONG,
    val categoryId: Long = DEFAULT_LONG,
    val uri: String = EMPTY_STRING,
    val pointName: String = EMPTY_STRING,
    val motivation: String = EMPTY_STRING,
    val reward: String = EMPTY_STRING,
    val date: Long = DEFAULT_LONG,
    val isActive: Boolean = true,
    val colActive: Long = DEFAULT_LONG,
    val colFinished: Long = DEFAULT_LONG,
)
