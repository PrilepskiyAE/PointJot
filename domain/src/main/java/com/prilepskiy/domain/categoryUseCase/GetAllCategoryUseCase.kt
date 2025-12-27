package com.prilepskiy.domain.categoryUseCase

import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.domain.model.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(): Flow<List<CategoryModel>> = repository.getAllCategory().map { model ->
        model.map {
            CategoryModel(
                categoryId = it.categoryId,
                categoryName = it.categoryName
            )
        }
    }.flowOn(Dispatchers.IO)
}