package com.lihan.noteapp.featrue.note.data.repository

import com.lihan.noteapp.core.data.local.NoteDao
import com.lihan.noteapp.core.domain.toNote
import com.lihan.noteapp.core.domain.toNoteEntity
import com.lihan.noteapp.featrue.note.domain.model.Note
import com.lihan.noteapp.featrue.note.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(
    private val dao: NoteDao
): NotesRepository{
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes().map {
            it.map { entity ->
                entity.toNote()
            }
        }
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toNoteEntity())
    }
}