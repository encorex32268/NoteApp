package com.lihan.noteapp.featrue.note.presentation.detail



sealed interface DetailAction {
    data class OnSelectColor(
        val color: Int
    ): DetailAction

    data class OnTitleValueChange(
        val text: String
    ): DetailAction

    data class OnDescriptionValueChange(
        val text: String
    ): DetailAction

    data object OnBackClick: DetailAction
    data object OnSaveClick: DetailAction
}