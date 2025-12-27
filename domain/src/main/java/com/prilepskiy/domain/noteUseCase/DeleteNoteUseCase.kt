package com.prilepskiy.domain.noteUseCase

import com.prilepskiy.data.repository.NoteRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository){
    operator fun invoke(id: Long) {
        repository.deleteNote(id)
    }
}