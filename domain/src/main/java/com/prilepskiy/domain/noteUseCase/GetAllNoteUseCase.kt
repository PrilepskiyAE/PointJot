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
class GetAllNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<NoteModel>> = repository.getAllNote().map { model ->
        model.map {
            NoteModel(
                noteId=it.noteId,
                pointId=it.pointId,
                uri=it.uri,
                note=it.note
            )
        }
    }.flowOn(Dispatchers.IO)
}