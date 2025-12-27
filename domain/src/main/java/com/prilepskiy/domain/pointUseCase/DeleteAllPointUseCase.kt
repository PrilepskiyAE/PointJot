package com.prilepskiy.domain.pointUseCase

import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.data.repository.PointRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteAllPointUseCase @Inject constructor(private val repository: PointRepository){
    operator fun invoke() {
        repository.deleteAll().flowOn(Dispatchers.IO).flowOn(Dispatchers.IO)
    }
}