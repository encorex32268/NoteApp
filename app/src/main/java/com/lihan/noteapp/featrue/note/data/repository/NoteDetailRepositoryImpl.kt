package com.lihan.noteapp.featrue.note.data.repository

import com.lihan.noteapp.core.data.local.NoteDao
import com.lihan.noteapp.core.domain.toNote
import com.lihan.noteapp.core.domain.toNoteEntity
import com.lihan.noteapp.featrue.note.domain.model.Note
import com.lihan.noteapp.featrue.note.domain.repository.NoteDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteDetailRepositoryImpl(
    private val dao: NoteDao
): NoteDetailRepository{
    override fun getNoteById(noteId: Int): Flow<Note> {
        return dao.getNoteById(noteId).map {
            it.toNote()
        }
    }

    override suspend fun createNote(note: Note) {
        dao.upsertNote(note.toNoteEntity())
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note.toNoteEntity())
    }
}