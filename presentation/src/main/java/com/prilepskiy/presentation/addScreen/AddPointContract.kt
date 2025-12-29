package com.prilepskiy.presentation.addScreen

import com.prilepskiy.common.MviAction
import com.prilepskiy.common.MviIntent
import com.prilepskiy.common.MviState
import com.prilepskiy.domain.model.CategoryModel

sealed class AddPointIntent : MviIntent {
    data class OnError(val error: String?) : AddPointIntent()
    data class OnLoading(val isLoading: Boolean) : AddPointIntent()
}

sealed class AddPointAction : MviAction {
    data class OnError(val error: String?) : AddPointAction()
    data class OnLoading(val isLoading: Boolean) : AddPointAction()
}

data class AddPointState(
    val categoryList: List<CategoryModel> = listOf(),
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : MviState