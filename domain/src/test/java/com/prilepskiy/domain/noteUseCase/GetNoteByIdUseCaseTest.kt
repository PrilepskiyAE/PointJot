package com.prilepskiy.domain.noteUseCase

import com.prilepskiy.data.repository.NoteRepository
import com.prilepskiy.domain.noteEntity
import com.prilepskiy.domain.noteModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetNoteByIdUseCaseTest {
    private lateinit var useCase: GetNoteByIdUseCase;
    private val mockRepository: NoteRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetNoteByIdUseCase(mockRepository)
    }
    @Test
    fun test1() = runTest {
        coEvery { mockRepository.getNoteById(1L) } returns flowOf(noteEntity)
        val result = useCase(1L).first()
        assertEquals(noteModel, result)
    }
    @Test
    fun test2() = runTest {
        coEvery { mockRepository.getNoteById(1L) } returns flowOf(noteEntity)
        useCase(1L).first()
        coVerify {  mockRepository.getNoteById(1L) }
    }
}