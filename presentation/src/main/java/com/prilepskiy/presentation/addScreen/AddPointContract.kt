package com.prilepskiy.presentation.addScreen

import androidx.compose.runtime.Immutable
import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.common.MviAction
import com.prilepskiy.common.MviIntent
import com.prilepskiy.common.MviState
import com.prilepskiy.domain.model.CategoryModel

sealed class AddPointIntent : MviIntent {
    data class InitPoint(val id: Long?) : AddPointIntent()
    data class OnError(val error: String?) : AddPointIntent()
    data class OnLoading(val isLoading: Boolean) : AddPointIntent()
    data class ChangePhotoPatch(val patch: String) : AddPointIntent()
    data class ChangePointName(val value: String) : AddPointIntent()
    data class ChangeMotivation(val value: String) : AddPointIntent()
    data class ChangeDate(val value: Long) : AddPointIntent()
    data class ChangeReward(val value: String) : AddPointIntent()
    data class ChangeCategory(val value: CategoryModel) : AddPointIntent()
    data class OnClickSave(val id: Long?,val onClick:()->Unit) : AddPointIntent()
}

sealed class AddPointAction : MviAction {
    data class InitPoint(
        val pointId: Long?,
        val pointName: String,
        val motivation: String,
        val reward: String,
        val selectedImageUri: String?,
        val selectedCategoryId: Long?,
        val date: Long
    ) : AddPointAction()

    data class OnError(val error: String?) : AddPointAction()
    data class OnLoading(val isLoading: Boolean) : AddPointAction()
    data class ChangePhotoPatch(val patch: String) : AddPointAction()
    data class ChangePointName(val value: String) : AddPointAction()
    data class ChangeMotivation(val value: String) : AddPointAction()
    data class ChangeDate(val value: Long) : AddPointAction()
    data class ChangeReward(val value: String) : AddPointAction()
    data class InitCategory(val value: List<CategoryModel>) : AddPointAction()
    data class ChangeCategory(val value: CategoryModel) : AddPointAction()
}
@Immutable
data class AddPointState(
    val pointId: Long = DEFAULT_LONG,
    val pointName: String = EMPTY_STRING,
    val motivation: String = EMPTY_STRING,
    val reward: String = EMPTY_STRING,
    val date: Long =  System.currentTimeMillis(),
    val selectedImageUri: String? = null,
    val categoryList: List<CategoryModel> = listOf(),
    val selectedCategory: CategoryModel? = null,
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : MviState