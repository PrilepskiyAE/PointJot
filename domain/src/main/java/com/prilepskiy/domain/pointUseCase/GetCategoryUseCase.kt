package com.prilepskiy.domain.pointUseCase

import com.prilepskiy.data.repository.PointRepository
import com.prilepskiy.data.repository.StageRepository
import com.prilepskiy.domain.model.PointModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCategoryUseCase @Inject constructor(
    private val repository: PointRepository,
    private val stageRepository: StageRepository
) {
    operator fun invoke(categoryId: Long): Flow<List<PointModel>> =
        repository.getCategory(categoryId).map { models ->
            models.map { model ->
                val allStage = stageRepository.getStageFromPoint(model.pointId).single()
                PointModel(
                    pointId = model.pointId,
                    categoryId = model.categoryId,
                    pointName = model.pointName,
                    uri = model.uri,
                    motivation = model.motivation,
                    reward = model.reward,
                    date = model.date,
                    isActive = model.isActive,
                    fullNote = allStage.size.toLong(),
                    colFinished = allStage.filter { it.isFinish }.size.toLong()
                )
            }
        }.flowOn(Dispatchers.IO)
}