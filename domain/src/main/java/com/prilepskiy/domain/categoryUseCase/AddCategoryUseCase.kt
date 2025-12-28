package com.prilepskiy.domain.categoryUseCase

import android.util.Log
import com.prilepskiy.data.database.entity.CategoryEntity
import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.domain.model.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
    suspend operator fun invoke(categoryModel: CategoryModel) {
        Log.d("TAG9999", "invoke: $categoryModel")
        repository.insert(
            CategoryEntity(
                categoryId = categoryModel.categoryId,
                categoryName = categoryModel.categoryName
            )
        )
    }
}