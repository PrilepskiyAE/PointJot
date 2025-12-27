package com.prilepskiy.domain.pointUseCase

import com.prilepskiy.data.database.entity.PointEntity
import com.prilepskiy.data.repository.PointRepository
import com.prilepskiy.domain.model.PointModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AddPointUseCase @Inject constructor(private val repository: PointRepository) {
    operator fun invoke(pointModel: PointModel) {
        repository.insertPoint(
            PointEntity(
                pointId = pointModel.pointId,
                categoryId = pointModel.categoryId,
                uri = pointModel.uri,
                motivation = pointModel.motivation,
                reward = pointModel.reward,
                date = pointModel.date,
                isActive = pointModel.isActive,
                colActive = pointModel.colActive,
                colFinished = pointModel.colFinished
            )
        ).flowOn(Dispatchers.IO)
    }
}