package com.lihan.noteapp.featrue.note.domain.repository

import com.lihan.noteapp.featrue.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteDetailRepository {
    fun getNoteById(noteId: Int): Flow<Note>
    suspend fun createNote(note: Note)
    suspend fun updateNote(note: Note)
}