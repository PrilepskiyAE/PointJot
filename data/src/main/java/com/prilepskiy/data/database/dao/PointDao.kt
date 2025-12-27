package com.prilepskiy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prilepskiy.data.database.entity.PointEntity

@Dao
interface PointDao {
    @Query("SELECT * FROM PointEntity")
    suspend fun getAll(): List<PointEntity>

    @Query("DELETE  FROM PointEntity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pointEntity: PointEntity)

    @Query("SELECT * FROM PointEntity WHERE pointId = :pointId")
    suspend fun getPoint(pointId: Long): List<PointEntity>

    @Query("SELECT * FROM PointEntity WHERE categoryId = :categoryId")
    suspend fun getCategory(categoryId: Long): List<PointEntity>

    @Query("DELETE FROM  PointEntity WHERE pointId=:id")
    suspend fun deletePoint(id: Long)
}