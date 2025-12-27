package com.prilepskiy.domain.stageUseCase

import com.prilepskiy.data.repository.StageRepository
import javax.inject.Inject

class DeleteStageUseCase @Inject constructor(private val repository: StageRepository){
    operator fun invoke(id: Long) {
        repository.deleteStage(id)
    }
}