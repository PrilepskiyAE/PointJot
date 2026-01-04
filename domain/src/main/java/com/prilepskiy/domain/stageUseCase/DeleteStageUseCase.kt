package com.prilepskiy.domain.stageUseCase

import com.prilepskiy.data.repository.StageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteStageUseCase @Inject constructor(private val repository: StageRepository) {
    suspend operator fun invoke(id: Long) {
        withContext(Dispatchers.IO) {
            repository.deleteStage(id)
        }
    }
}