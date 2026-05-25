package com.prilepskiy.domain.categoryUseCase

import com.prilepskiy.data.database.entity.CategoryEntity
import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.data.repository.PointRepository
import com.prilepskiy.domain.categoryModel
import com.prilepskiy.domain.model.CategoryModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteCategoryUseCaseTest {
    private lateinit var useCase: DeleteCategoryUseCase
    private val mockRepository: CategoryRepository = mockk()
    private val mokkRepositoryPoint: PointRepository = mockk()

    @Before
    fun setUp() {
        useCase = DeleteCategoryUseCase(mockRepository, mokkRepositoryPoint)
    }
    @Test
    fun test1() = runTest {

        coEvery {
            mockRepository.deleteCategory(categoryModel.categoryId)
            mokkRepositoryPoint.deletePointByCategory(categoryModel.categoryId)
        } coAnswers { Unit }
        useCase(categoryModel.categoryId)
        coVerify {
            mockRepository.deleteCategory(categoryModel.categoryId)
            mokkRepositoryPoint.deletePointByCategory(categoryModel.categoryId)
        }
    }
}