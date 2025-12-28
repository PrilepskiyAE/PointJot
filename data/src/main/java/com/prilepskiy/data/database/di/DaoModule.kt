package com.prilepskiy.data.database.di

import com.prilepskiy.data.database.PointDataBase
import com.prilepskiy.data.database.dao.CategoryDao
import com.prilepskiy.data.database.dao.NoteDao
import com.prilepskiy.data.database.dao.PointDao
import com.prilepskiy.data.database.dao.StageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NoteDaoModule {
    @Provides
    fun provideNoteDao(database: PointDataBase): NoteDao =
        database.noteDao
}

@Module
@InstallIn(SingletonComponent::class)
object PointDaoModule {
    @Provides
    fun providePointDao(database: PointDataBase): PointDao =
        database.pointDao
}

@Module
@InstallIn(SingletonComponent::class)
object StageDaoModule {
    @Provides
    fun provideStageDao(database: PointDataBase): StageDao =
        database.stageDao
}

@Module
@InstallIn(SingletonComponent::class)
object CategoryDaoModule {
    @Provides
    fun provideStageDao(database: PointDataBase): CategoryDao =
        database.categoryDao
}
