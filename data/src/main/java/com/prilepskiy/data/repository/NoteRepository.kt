package com.prilepskiy.data.repository

import com.prilepskiy.common.emitFlow
import com.prilepskiy.data.database.dao.NoteDao
import com.prilepskiy.data.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val dao: NoteDao,
) {

    fun getAllNote(): Flow<List<NoteEntity>> = emitFlow {
        dao.getAllNote()
    }


    fun getNoteFromPoint(id: Long): Flow<List<NoteEntity>> = emitFlow {
        dao.getNoteFromPoint(id)
    }


    suspend fun insert(noteEntity: NoteEntity) {
        dao.insert(noteEntity)
    }


    fun getNoteById(id: Long): Flow<NoteEntity> = emitFlow {
        dao.getNoteById(id)
    }


    suspend fun deleteNote(id: Long) {
        dao.deleteNote(id)
    }

    suspend fun deleteNotesForPoint(id: Long) {
        dao.deleteNotesForPoint(id)
    }

}