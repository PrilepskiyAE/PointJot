package com.prilepskiy.domain.noteUseCase

import com.prilepskiy.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Long) {
        withContext(Dispatchers.IO) {
            repository.deleteNote(id)
        }
    }
}