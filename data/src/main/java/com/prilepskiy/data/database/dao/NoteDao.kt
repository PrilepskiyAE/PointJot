package com.prilepskiy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prilepskiy.data.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    suspend fun getAllNote(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE pointId=:id")
    suspend fun getNoteFromPoint(id: Long): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity)

    @Query("SELECT * FROM  NoteEntity WHERE pointId=:id")
    suspend fun getNoteById(id: Long): NoteEntity

    @Query("DELETE FROM  NoteEntity WHERE noteId=:id")
    suspend fun deleteNote(id: Long)


}