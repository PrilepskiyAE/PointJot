package com.prilepskiy.domain.categoryUseCase

import com.prilepskiy.data.database.entity.CategoryEntity
import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.domain.model.CategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
    suspend operator fun invoke(categoryModel: CategoryModel) {
        repository.insert(
            CategoryEntity(
                categoryId = categoryModel.categoryId,
                categoryName = categoryModel.categoryName
            )
        )
    }
}