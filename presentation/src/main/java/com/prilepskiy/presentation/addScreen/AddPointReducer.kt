package com.prilepskiy.presentation.addScreen

import com.prilepskiy.common.DEFAULT_LONG
import com.prilepskiy.common.ID_ALL_CATEGORY
import com.prilepskiy.common.Reducer
import javax.inject.Inject

class AddPointReducer @Inject constructor() : Reducer<AddPointAction, AddPointState> {
    override fun reduce(action: AddPointAction, state: AddPointState): AddPointState =
        when (action) {
            is AddPointAction.OnError -> state.copy(error = action.error, isLoading = false)
            is AddPointAction.OnLoading -> state.copy(isLoading = action.isLoading)
            is AddPointAction.ChangePhotoPatch -> state.copy(selectedImageUri = action.patch)
            is AddPointAction.ChangeDate -> state.copy(date = action.value)
            is AddPointAction.ChangeMotivation -> state.copy(motivation = action.value)
            is AddPointAction.ChangePointName -> state.copy(pointName = action.value)
            is AddPointAction.ChangeReward -> state.copy(reward = action.value)
            is AddPointAction.ChangeCategory -> state.copy(selectedCategory = action.value)
            is AddPointAction.InitCategory -> {
                val res = action.value.filter { it.categoryId != ID_ALL_CATEGORY }
                state.copy(
                    selectedCategory = res.firstOrNull(),
                    categoryList = res,
                    isLoading = false
                )
            }

            is AddPointAction.InitPoint -> {
                val selectedCategory=state.categoryList.find { it.categoryId==action.selectedCategoryId }
                state.copy(
                    pointId = action.pointId ?: DEFAULT_LONG,
                    pointName = action.pointName,
                    motivation=action.motivation,
                    reward=action.reward,
                    selectedCategory = selectedCategory,
                    selectedImageUri = action.selectedImageUri,
                    date = action.date
                )
            }
        }

}