package com.prilepskiy.data.repository

import com.prilepskiy.common.emitFlow
import com.prilepskiy.data.database.dao.StageDao
import com.prilepskiy.data.database.entity.StageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StageRepository @Inject constructor(
    private val dao: StageDao,
) {
    fun getAllStage(): Flow<List<StageEntity>> = emitFlow {
        dao.getAllStage()
    }

    fun getStageFromPoint(id: Long): Flow<List<StageEntity>> = emitFlow {
        dao.getStageFromPoint(id)
    }

    fun insert(stageEntity: StageEntity) = emitFlow {
        dao.insert(stageEntity)
    }

    fun getStageById(id: Long): Flow<StageEntity> = emitFlow {
        dao.getStageById(id)
    }


    fun deleteStage(id: Long) = emitFlow { dao.deleteStage(id) }
}