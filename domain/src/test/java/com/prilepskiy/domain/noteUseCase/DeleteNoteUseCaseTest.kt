package com.prilepskiy.domain.noteUseCase

import com.prilepskiy.data.repository.NoteRepository
import com.prilepskiy.domain.noteEntity
import com.prilepskiy.domain.noteModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseTest {
    private lateinit var useCase: DeleteNoteUseCase;
    private val mockRepository: NoteRepository = mockk()

    @Before
    fun setUp() {
        useCase = DeleteNoteUseCase(mockRepository)
    }
    @Test
    fun test1() = runTest {
        coEvery { mockRepository.deleteNote(noteModel.noteId) } coAnswers { Unit }
        useCase(noteModel.noteId)
        coVerify {mockRepository.deleteNote(noteModel.noteId)}
    }

}