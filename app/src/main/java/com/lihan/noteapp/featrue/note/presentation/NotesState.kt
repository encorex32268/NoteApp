package com.lihan.noteapp.featrue.note.presentation

import com.lihan.noteapp.featrue.note.domain.model.Note

data class NotesState(
    val items: List<Note> = emptyList(),
    val searchText: String = ""
)
