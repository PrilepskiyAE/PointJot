package com.prilepskiy.domain.noteUseCase

import com.prilepskiy.data.repository.NoteRepository
import com.prilepskiy.domain.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNoteByIdUseCase  @Inject constructor(private val repository: NoteRepository) {
    operator fun invoke(id: Long): Flow<NoteModel> {
        return repository.getNoteById(id).map { model ->
            NoteModel(
                noteId=model.noteId,
                pointId=model.pointId,
                uri=model.uri,
                note=model.note
            )
        }.flowOn(Dispatchers.IO)
    }
}