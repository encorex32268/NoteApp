package com.lihan.noteapp.featrue.note.presentation

import com.lihan.noteapp.featrue.note.domain.model.Note

sealed interface NotesAction {
    data class NavigateToDetail(
        val id: Int?=null
    ): NotesAction
    data class OnSearchNote(
        val title: String
    ): NotesAction

    data class OnDeleteNote(
        val note: Note
    ): NotesAction
}