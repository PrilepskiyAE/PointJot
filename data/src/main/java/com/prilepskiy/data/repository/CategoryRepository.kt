package com.prilepskiy.data.repository

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
    init {

    }

    fun getAllCategory(): Flow<List<CategoryEntity>> = emitFlow {
        dao.getAllCategory()
    }


    fun insert(categoryEntity: CategoryEntity) = emitFlow {
        dao.insert(categoryEntity)
    }


    fun deleteCategory(id: Long) = emitFlow {
        dao.deleteCategory(id)
    }
}