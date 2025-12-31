package com.prilepskiy.domain.pointUseCase

import com.prilepskiy.data.repository.PointRepository
import com.prilepskiy.domain.model.PointModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCategoryUseCase @Inject constructor(private val repository: PointRepository) {
    operator fun invoke(categoryId: Long): Flow<List<PointModel>> = repository.getCategory(categoryId).map { model ->
        model.map {
            PointModel(
                pointId=it.pointId,
                categoryId = it.categoryId,
                pointName = it.pointName,
                uri = it.uri,
                motivation=it.motivation,
                reward=it.reward,
                date=it.date,
                isActive=it.isActive,
                colActive=it.colActive,
                colFinished=it.colFinished
            )
        }
    }.flowOn(Dispatchers.IO)
}