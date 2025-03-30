package com.lihan.noteapp.featrue.note.presentation

sealed interface NotesAction {
    data class NavigateToDetail(
        val id: Int?=null
    ): NotesAction
    data class OnSearchNote(
        val title: String
    ): NotesAction

    data class OnDeleteNote(
        val id: Int?=null
    ): NotesAction
}