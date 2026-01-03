package com.prilepskiy.presentation.detailScreen

import com.prilepskiy.common.ID_ALL_CATEGORY
import com.prilepskiy.common.MviAction
import com.prilepskiy.common.MviIntent
import com.prilepskiy.common.MviState
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.domain.model.NoteModel
import com.prilepskiy.domain.model.PointModel
import com.prilepskiy.domain.model.StageModel


sealed class DetailIntent : MviIntent {
    data class Init(val first: String,val second: String) : DetailIntent()
    data class OnError(val error: String?) : DetailIntent()
    data class OnLoading(val isLoading: Boolean) : DetailIntent()
}

sealed class DetailAction : MviAction {
    data class Init(val first: String,val second: String) : DetailAction()
    data class OnError(val error: String?) : DetailAction()
    data class OnLoading(val isLoading: Boolean) : DetailAction()

}

data class DetailState(
    val point: PointModel? = null,
    val categoryList: List<CategoryModel> = listOf(),
    val stageList: List<StageModel> = listOf(),
    val noteList: List<NoteModel> = listOf(),
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : MviState