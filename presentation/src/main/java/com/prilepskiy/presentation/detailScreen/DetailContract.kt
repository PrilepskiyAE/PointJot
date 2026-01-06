package com.prilepskiy.presentation.detailScreen

import com.prilepskiy.common.MviAction
import com.prilepskiy.common.MviIntent
import com.prilepskiy.common.MviState
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.domain.model.NoteModel
import com.prilepskiy.domain.model.PointModel
import com.prilepskiy.domain.model.StageModel


sealed class DetailIntent : MviIntent {
    data class Init(val pointId: Long) : DetailIntent()
    data class OnClickSuccess(val onSuccess:()-> Unit) : DetailIntent()
    data class OnClickDelete(val onDelete:()-> Unit) : DetailIntent()
    data class OnError(val error: String?) : DetailIntent()
    data class OnLoading(val isLoading: Boolean) : DetailIntent()
    data class AddNote(val noteModel: NoteModel) : DetailIntent()
    data class AddStage(val stageModel: StageModel) : DetailIntent()
    data class OnSuccessStage(val stageModel: StageModel,val onSuccess:(List<StageModel>)-> Unit): DetailIntent()
    data class OnDeleteStage(val stageModel: StageModel): DetailIntent()
    data class OnDeleteNote(val noteModel: NoteModel): DetailIntent()
}

sealed class DetailAction : MviAction {
    data class Init(val point: PointModel) : DetailAction()
    data class GetCategory(val category: List<CategoryModel>) : DetailAction()
    data class OnError(val error: String?) : DetailAction()
    data class OnLoading(val isLoading: Boolean) : DetailAction()
    data class GetStage(val stageList: List<StageModel>) : DetailAction()
    data class GetNote(val noteList: List<NoteModel>) : DetailAction()

}

data class DetailState(
    val point: PointModel? = null,
    val categoryList: List<CategoryModel> = listOf(),
    val stageList: List<StageModel> = listOf(),
    val noteList: List<NoteModel> = listOf(),
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : MviState