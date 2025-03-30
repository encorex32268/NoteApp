package com.lihan.noteapp.featrue.note.domain.repository

import com.lihan.noteapp.featrue.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun deleteNote(note: Note)
}