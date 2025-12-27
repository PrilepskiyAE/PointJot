package com.prilepskiy.domain.model

import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.EMPTY_STRING

data class StageModel(
    val stageId: Long = DEFAULT_LONG,
    val pointId: Long = DEFAULT_LONG,
    val title: String = EMPTY_STRING,
    val label: String = EMPTY_STRING,
    val isActive: Boolean = true,
)
