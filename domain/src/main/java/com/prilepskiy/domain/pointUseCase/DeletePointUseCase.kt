package com.prilepskiy.domain.pointUseCase

import com.prilepskiy.data.repository.PointRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DeletePointUseCase @Inject constructor(private val repository: PointRepository){
    operator fun invoke(id: Long) {
        repository.deletePoint(id).flowOn(Dispatchers.IO)
    }
}