package com.prilepskiy.domain.categoryUseCase

import com.prilepskiy.data.repository.CategoryRepository
import com.prilepskiy.domain.expectedCategoryModels
import com.prilepskiy.domain.mockCategoryEntities
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
    fun test1() = runTest {

        coEvery { mockRepository.getAllCategory() } returns flowOf(mockCategoryEntities)

        val resultFlow = useCase()
        var result: List<CategoryModel>? = null

        resultFlow.collect { collectedList ->
            result = collectedList
        }
        assertEquals(expectedCategoryModels, result)
    }

    @Test
    fun test2() = runTest {
        coEvery { mockRepository.getAllCategory() } returns flowOf(emptyList())
        val resultFlow = useCase()
        var result: List<CategoryModel>? = null

        resultFlow.collect { collectedList ->
            result = collectedList
        }
        assertEquals(emptyList<CategoryModel>(), result)
    }
}