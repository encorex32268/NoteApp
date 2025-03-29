package com.lihan.noteapp.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id =:noteId")
    fun getNoteById(noteId: Int): Flow<NoteEntity>

    @Upsert
    suspend fun upsertNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

}