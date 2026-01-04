package com.prilepskiy.domain.pointUseCase

import com.prilepskiy.data.database.entity.PointEntity
import com.prilepskiy.data.repository.PointRepository
import com.prilepskiy.data.repository.StageRepository
import com.prilepskiy.domain.model.PointModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddPointUseCase @Inject constructor(
    private val repository: PointRepository,
    private val stageRepository: StageRepository
) {
    suspend operator fun invoke(pointModel: PointModel) {
        withContext(Dispatchers.IO) {
            val allStage = stageRepository.getStageFromPoint(pointModel.pointId).single()
            repository.insertPoint(
                PointEntity(
                    pointId = pointModel.pointId,
                    categoryId = pointModel.categoryId,
                    pointName = pointModel.pointName,
                    uri = pointModel.uri,
                    motivation = pointModel.motivation,
                    reward = pointModel.reward,
                    date = pointModel.date,
                    isActive = pointModel.isActive,
                    fullNote = allStage.size.toLong(),
                    colFinished = allStage.filter { it.isFinish }.size.toLong()
                )
            )
        }
    }
}