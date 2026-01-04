package com.prilepskiy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prilepskiy.data.database.entity.StageEntity

@Dao
interface StageDao {
    @Query("SELECT * FROM StageEntity")
    suspend fun getAllStage(): List<StageEntity>

    @Query("SELECT * FROM StageEntity WHERE pointId=:id")
    suspend fun getStageFromPoint(id: Long): List<StageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: StageEntity)

    @Query("SELECT * FROM  StageEntity WHERE pointId=:id")
    suspend fun getStageById(id: Long): StageEntity

    @Query("DELETE FROM  StageEntity WHERE stageId=:id")
    suspend fun deleteStage(id: Long)

    @Query("DELETE FROM  StageEntity WHERE pointId=:id")
    suspend fun deleteStageForPoint(id: Long)

}