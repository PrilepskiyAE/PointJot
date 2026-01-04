package com.prilepskiy.domain.pointUseCase

import com.prilepskiy.data.repository.PointRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteAllPointUseCase @Inject constructor(private val repository: PointRepository) {
    operator fun invoke() {
        @Suppress("UnusedFlow")
        repository.deleteAll()
    }
}