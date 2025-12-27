package com.prilepskiy.domain.stageUseCase

import com.prilepskiy.data.repository.StageRepository
import com.prilepskiy.domain.model.StageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllStageUseCase @Inject constructor(private val repository: StageRepository){
    operator fun invoke(): Flow<List<StageModel>> = repository.getAllStage().map { model ->
        model.map {
            StageModel(
                stageId = it.stageId,
                pointId = it.pointId,
                title=it.title,
                label = it.label,
                isActive = it.isActive
            )
        }
    }.flowOn(Dispatchers.IO)
}