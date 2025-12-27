package com.prilepskiy.data.repository

import com.prilepskiy.common.emitFlow
import com.prilepskiy.data.database.dao.PointDao
import com.prilepskiy.data.database.entity.PointEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PointRepository @Inject constructor(
    private val dao: PointDao,
) {

    fun getAll(): Flow<List<PointEntity>> = emitFlow {
        dao.getAll()
    }


    fun deleteAll() = emitFlow {
        dao.deleteAll()
    }


    fun insertPoint(pointEntity: PointEntity) = emitFlow {
        dao.insert(pointEntity)
    }

    fun getPoint(pointId: Long): Flow<List<PointEntity>> = emitFlow {
        dao.getPoint(pointId)
    }


    fun getCategory(categoryId: Long): Flow<List<PointEntity>> = emitFlow {
        dao.getCategory(categoryId)
    }

}