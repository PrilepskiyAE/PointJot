package com.prilepskiy.domain.stageUseCase

import com.prilepskiy.data.database.entity.StageEntity
import com.prilepskiy.data.repository.StageRepository
import com.prilepskiy.domain.model.StageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddStageUseCase @Inject constructor(private val repository: StageRepository) {
    operator fun invoke(stageModel: StageModel) {
        repository.insert(
            StageEntity(
                stageId = stageModel.stageId,
                pointId = stageModel.pointId,
                title = stageModel.title,
                label = stageModel.label,
                isActive = stageModel.isActive
            )
        ).flowOn(Dispatchers.IO)
    }
}