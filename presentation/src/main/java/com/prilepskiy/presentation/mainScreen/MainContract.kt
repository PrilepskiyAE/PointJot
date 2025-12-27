package com.prilepskiy.presentation.mainScreen

import com.prilepskiy.common.MviAction
import com.prilepskiy.common.MviIntent
import com.prilepskiy.common.MviState
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.domain.model.PointModel

sealed class MainIntent : MviIntent {
    data class OnClick(val pointId: Int) : MainIntent()
    data class OnError(val error: String?) : MainIntent()
    data class OnLoading(val isLoading: Boolean) : MainIntent()
}

sealed class MainAction : MviAction {
    data class OnError(val error: String?) : MainAction()
    data class OnLoading(val isLoading: Boolean) : MainAction()
}

data class MainState(
    val categoryList: List<CategoryModel> = listOf(),
    val pointList: List<PointModel> = listOf(),
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : MviState