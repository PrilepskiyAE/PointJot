package com.prilepskiy.domain.model

import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.EMPTY_STRING

data class CategoryModel(
    val categoryId: Long = DEFAULT_LONG,
    val categoryName: String = EMPTY_STRING,
)
