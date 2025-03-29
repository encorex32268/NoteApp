package com.lihan.noteapp.core.presentation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Notes: Route

    @Serializable
    data class NoteDetail(
        val noteId: Int?
    ): Route
}