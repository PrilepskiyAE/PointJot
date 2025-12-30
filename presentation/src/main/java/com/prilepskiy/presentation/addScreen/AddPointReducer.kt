package com.prilepskiy.presentation.addScreen

import com.prilepskiy.common.Reducer
import javax.inject.Inject

class AddPointReducer @Inject constructor() : Reducer<AddPointAction, AddPointState> {
    override fun reduce(action: AddPointAction, state: AddPointState): AddPointState = when (action) {
        is AddPointAction.OnError -> state.copy(error = action.error, isLoading = false)
        is AddPointAction.OnLoading ->  state.copy(isLoading = action.isLoading)
        is AddPointAction.ChangePhotoPatch -> state.copy(selectedImageUri = action.patch)
        is AddPointAction.ChangeDate -> state.copy(date = action.value)
        is AddPointAction.ChangeMotivation ->state.copy(motivation = action.value)
        is AddPointAction.ChangePointName -> state.copy(pointName = action.value)
        is AddPointAction.ChangeReward -> state.copy(reward = action.value)
    }

}