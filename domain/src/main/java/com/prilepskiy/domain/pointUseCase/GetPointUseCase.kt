package com.prilepskiy.domain.pointUseCase

import com.prilepskiy.data.repository.PointRepository
import com.prilepskiy.data.repository.StageRepository
import com.prilepskiy.domain.model.PointModel
import com.prilepskiy.domain.model.StageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPointUseCase@Inject constructor(private val repository: PointRepository) {
    operator fun invoke(id: Long): Flow<List<PointModel>> {
        return repository.getPoint(id).map { model ->
            model.map {
                PointModel(
                    pointId=it.pointId,
                    categoryId = it.categoryId,
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
}

