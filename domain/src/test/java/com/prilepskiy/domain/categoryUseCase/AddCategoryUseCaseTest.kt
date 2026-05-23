package com.prilepskiy.domain.categoryUseCase

import com.prilepskiy.data.database.entity.CategoryEntity
import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.domain.model.CategoryModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddCategoryUseCaseTest {
    private lateinit var useCase: AddCategoryUseCase
    private val mockRepository: CategoryRepository = mockk()
    @Before
    fun setUp() {
        useCase = AddCategoryUseCase(mockRepository)
    }

    @Test
    fun test1() = runTest {

        val categoryModel = CategoryModel(
            categoryId = 1,
            categoryName = "Test Category"
        )
        val expectedEntity = CategoryEntity(
            categoryId = categoryModel.categoryId,
            categoryName = categoryModel.categoryName
        )
        coEvery { mockRepository.insert(expectedEntity) } coAnswers { Unit }

        useCase(categoryModel)

        coVerify { mockRepository.insert(expectedEntity) }
    }
}