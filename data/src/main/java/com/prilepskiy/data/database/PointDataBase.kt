package com.prilepskiy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prilepskiy.common.VERSION_DATABASE
import com.prilepskiy.data.database.dao.NoteDao
import com.prilepskiy.data.database.dao.PointDao
import com.prilepskiy.data.database.dao.StageDao
import com.prilepskiy.data.database.entity.NoteEntity
import com.prilepskiy.data.database.entity.PointEntity
import com.prilepskiy.data.database.entity.StageEntity


@Database(
    entities = [NoteEntity::class, StageEntity::class, PointEntity::class] ,
    version = VERSION_DATABASE,
    exportSchema = true
)

abstract class  PointDataBase : RoomDatabase() {
    abstract val pointDao: PointDao
    abstract val stageDao: StageDao
    abstract val noteDao: NoteDao
}