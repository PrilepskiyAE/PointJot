package com.prilepskiy.domain.categoryUseCase

import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.domain.model.CategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteCategoryUseCase @Inject constructor(private val repository: CategoryRepository){
    operator fun invoke(id: Long) {
        repository.deleteCategory(id)
    }
}