package com.prilepskiy.domain.noteUseCase

import com.prilepskiy.data.repository.NoteRepository
import com.prilepskiy.domain.expectedEntity
import com.prilepskiy.domain.noteEntity
import com.prilepskiy.domain.noteModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {
    private lateinit var useCase: AddNoteUseCase;
    private val mockRepository: NoteRepository = mockk()

    @Before
    fun setUp() {
        useCase = AddNoteUseCase(mockRepository)
    }
    @Test
    fun test1() = runTest {
        coEvery { mockRepository.insert(noteEntity) } coAnswers { Unit }
        useCase(noteModel = noteModel)
        coVerify {mockRepository.insert(noteEntity)}
    }

}