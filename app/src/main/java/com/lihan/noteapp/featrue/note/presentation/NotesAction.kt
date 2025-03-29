package com.lihan.noteapp.featrue.note.presentation

sealed interface NotesAction {
    data class NavigateToDetail(
        val id: Int?=null
    ): NotesAction
    data class OnNoteSearch(
        val title: String
    ): NotesAction
}