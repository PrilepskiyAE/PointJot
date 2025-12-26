package com.prilepskiy.data.database.di

import android.content.Context
import androidx.room.Room
import com.prilepskiy.common.DATABASE_NAME
import com.prilepskiy.data.database.PointDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): PointDataBase =
        Room.databaseBuilder(
            context = context,
            klass = PointDataBase::class.java,
            name = DATABASE_NAME
        ).build()
}