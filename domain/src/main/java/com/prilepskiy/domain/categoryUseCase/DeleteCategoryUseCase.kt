package com.prilepskiy.domain.categoryUseCase

import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.data.repository.PointRepository
import com.prilepskiy.domain.model.CategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteCategoryUseCase @Inject constructor(private val repository: CategoryRepository,private val repositoryPoint: PointRepository){
   suspend operator fun invoke(id: Long) {
        repository.deleteCategory(id)
        repositoryPoint.deletePointByCategory(id)
    }
}