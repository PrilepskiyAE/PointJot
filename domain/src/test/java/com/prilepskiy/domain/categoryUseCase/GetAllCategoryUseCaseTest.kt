package com.prilepskiy.domain.categoryUseCase

import com.prilepskiy.data.database.entity.CategoryEntity
import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.domain.model.CategoryModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllCategoryUseCaseTest {
    private lateinit var useCase: GetAllCategoryUseCase
    private val mockRepository: CategoryRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetAllCategoryUseCase(mockRepository)
    }
    @Test
    fun test1() = runTest{
        val mockEntities = listOf(
            CategoryEntity(categoryId = 1, categoryName = "Category 1"),
            CategoryEntity(categoryId = 2, categoryName = "Category 2"),
            CategoryEntity(categoryId = 3, categoryName = "Category 3")
        )

        val expectedModels = listOf(
            CategoryModel(categoryId = 1, categoryName = "Category 1", isActive = true),
            CategoryModel(categoryId = 2, categoryName = "Category 2", isActive = false),
            CategoryModel(categoryId = 3, categoryName = "Category 3", isActive = false)
        )
        coEvery { mockRepository.getAllCategory() } returns flowOf(mockEntities)

        val resultFlow = useCase()
        var result: List<CategoryModel>? = null

        resultFlow.collect { collectedList ->
            result = collectedList
        }
        assertEquals(expectedModels, result)
    }
    @Test
    fun test2() = runTest{
        coEvery { mockRepository.getAllCategory() } returns flowOf(emptyList())
        val resultFlow = useCase()
        var result: List<CategoryModel>? = null

        resultFlow.collect { collectedList ->
            result = collectedList
        }
        assertEquals(emptyList<CategoryModel>(), result)
    }
}