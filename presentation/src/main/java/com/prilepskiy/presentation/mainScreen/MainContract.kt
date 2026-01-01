package com.prilepskiy.presentation.mainScreen

import androidx.compose.ui.res.stringResource
import com.prilepskiy.common.MviAction
import com.prilepskiy.common.MviIntent
import com.prilepskiy.common.MviState
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.domain.model.PointModel
import com.prilepskiy.presentation.R

sealed class MainIntent : MviIntent {
    data class InitPoint(val first: String,val second: String) : MainIntent()
    data class OnClickPoint(val pointId: Int) : MainIntent()
    data class OnError(val error: String?) : MainIntent()
    data class OnLoading(val isLoading: Boolean) : MainIntent()
    data class OnClickCategory(val item: CategoryModel): MainIntent()
    data class AddCategory(val item: CategoryModel): MainIntent()
    data class DeleteCategory(val item: CategoryModel): MainIntent()
}

sealed class MainAction : MviAction {
    data class OnError(val error: String?) : MainAction()
    data class OnLoading(val isLoading: Boolean) : MainAction()
    data class GetCategory( val categoryList: List<CategoryModel>) : MainAction()
    data class OnClickCategory(val item: CategoryModel) : MainAction()
    data class AddCategory(val item: CategoryModel): MainAction()
    data class DeleteCategory(val item: CategoryModel): MainAction()
    data class GetPoint(val pointList: List<PointModel>): MainAction()
}

data class MainState(
    val categoryList: List<CategoryModel> = listOf(),
    val pointList: List<PointModel> = listOf(),
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : MviState