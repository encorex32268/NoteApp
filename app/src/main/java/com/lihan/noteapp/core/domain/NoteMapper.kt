package com.lihan.noteapp.core.domain

import com.lihan.noteapp.core.data.local.NoteEntity
import com.lihan.noteapp.featrue.note.domain.model.Note

fun NoteEntity.toNote(): Note{
    return Note(
        id = id,
        title = title,
        description = description,
        color = color,
        timestamp = timestamp
    )
}

fun Note.toNoteEntity(): NoteEntity{
    return NoteEntity(
        id = id,
        title = title,
        description = description,
        color = color,
        timestamp = timestamp
    )
}