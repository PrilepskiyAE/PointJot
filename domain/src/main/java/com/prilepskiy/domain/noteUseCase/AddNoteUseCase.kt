package com.prilepskiy.domain.noteUseCase

import com.prilepskiy.data.database.entity.NoteEntity
import com.prilepskiy.data.repository.NoteRepository
import com.prilepskiy.domain.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(noteModel: NoteModel) {
        withContext(Dispatchers.IO) {
            repository.insert(
                NoteEntity(
                    noteId = noteModel.noteId,
                    pointId = noteModel.pointId,
                    uri = noteModel.uri,
                    note = noteModel.note,
                    date= noteModel.date
                )
            )
        }
    }
}