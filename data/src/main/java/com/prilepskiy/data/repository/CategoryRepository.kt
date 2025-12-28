package com.prilepskiy.data.repository

import android.util.Log
import com.prilepskiy.common.emitFlow
import com.prilepskiy.data.database.dao.CategoryDao
import com.prilepskiy.data.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val dao: CategoryDao,
) {
    fun getAllCategory(): Flow<List<CategoryEntity>> = emitFlow {
        dao.getAllCategory()
    }


    suspend fun insert(categoryEntity: CategoryEntity) {
        dao.insert(categoryEntity)
    }


    suspend fun deleteCategory(id: Long){
        dao.deleteCategory(id)

    }
}