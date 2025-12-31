package com.prilepskiy.domain.pointUseCase

import android.util.Log
import com.prilepskiy.data.database.entity.PointEntity
import com.prilepskiy.data.repository.PointRepository
import com.prilepskiy.domain.model.PointModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AddPointUseCase @Inject constructor(private val repository: PointRepository) {
    suspend operator fun invoke(pointModel: PointModel) {
        withContext(Dispatchers.IO) {
            Log.d("TAG999", "OnClickSave2: $pointModel")
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
                    colActive = pointModel.colActive,
                    colFinished = pointModel.colFinished
                )
            )
        }
    }
}