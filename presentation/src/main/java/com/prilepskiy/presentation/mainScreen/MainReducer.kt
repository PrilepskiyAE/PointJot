package com.prilepskiy.presentation.mainScreen

import com.prilepskiy.common.Reducer
import javax.inject.Inject

class MainReducer @Inject constructor() : Reducer<MainAction, MainState> {
    override fun reduce(action: MainAction, state: MainState): MainState = when (action) {
        is MainAction.OnLoading -> state.copy(isLoading = action.isLoading)
        is MainAction.OnError -> state.copy(error = action.error, isLoading = false)
        is MainAction.GetCategory -> state.copy(categoryList = action.categoryList)
        is MainAction.AddCategory -> {
            val list = state.categoryList.toMutableList()
            list.add(action.item)
            state.copy(categoryList = list, isLoading = false)
        }

        is MainAction.DeleteCategory -> {
            val list = state.categoryList.toMutableList()
            list.remove(action.item)
            state.copy(categoryList = list, isLoading = false)
        }

        is MainAction.OnClickCategory -> {
            val result = state.categoryList.map { item ->
                if (item == action.item) {
                    item.copy(isActive = true)
                } else {
                    item.copy(isActive = false)
                }
            }
            state.copy(categoryList = result, activeCategoryId = action.item.categoryId)
        }

        is MainAction.GetPoint -> state.copy(pointList = action.pointList, isLoading = false)
        is MainAction.OnClickTab -> {
            state.copy(isActive = action.isActive,)
        }
    }
}