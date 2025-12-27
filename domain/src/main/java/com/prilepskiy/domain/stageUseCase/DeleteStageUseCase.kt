package com.prilepskiy.domain.stageUseCase

import com.prilepskiy.data.repository.StageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteStageUseCase @Inject constructor(private val repository: StageRepository){
    operator fun invoke(id: Long) {
        repository.deleteStage(id).flowOn(Dispatchers.IO)
    }
}