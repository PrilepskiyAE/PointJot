package com.prilepskiy.domain.noteUseCase

import com.prilepskiy.data.repository.NoteRepository
import com.prilepskiy.domain.expectedCategoryModels
import com.prilepskiy.domain.expectedNoteModels
import com.prilepskiy.domain.mockNoteEntities
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.domain.model.NoteModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllNoteUseCaseTest {
    private lateinit var useCase: GetAllNoteUseCase;
    private val mockRepository: NoteRepository = mockk()

    @Before
    fun setUp() {
        useCase =  GetAllNoteUseCase(mockRepository)
    }
    @Test
    fun test1() = runTest {
        coEvery { mockRepository.getAllNote() } returns flowOf(mockNoteEntities)
        val resultFlow = useCase()
        var result: List<NoteModel>? = null

        resultFlow.collect { collectedList ->
            result = collectedList
        }

        assertEquals(expectedNoteModels, result)
    }
    @Test
    fun test2() = runTest {
        coEvery { mockRepository.getAllNote() } returns flowOf(mockNoteEntities)
        useCase()
        coVerify { mockRepository.getAllNote() }
    }

    @Test
    fun test3() = runTest {
        coEvery { mockRepository.getAllNote() } returns flowOf(emptyList())
        val resultFlow = useCase()
        var result: List<NoteModel>? = null

        resultFlow.collect { collectedList ->
            result = collectedList
        }
        assertEquals(emptyList<NoteModel>(), result)
    }
}

