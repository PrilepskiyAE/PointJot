package com.prilepskiy.domain.stageUseCase

import com.prilepskiy.data.repository.StageRepository
import com.prilepskiy.domain.model.StageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStageByIdUseCase @Inject constructor(private val repository: StageRepository) {
    operator fun invoke(id: Long): Flow<StageModel> {
        return repository.getStageById(id).map { model ->
            StageModel(
                stageId = model.stageId,
                pointId = model.pointId,
                title = model.title,
                label = model.label,
                isActive = model.isActive
            )
        }.flowOn(Dispatchers.IO)
    }
}
