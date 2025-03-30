package com.lihan.noteapp.featrue.note.presentation.detail

sealed interface DetailUiEvent {
    data object Nothing: DetailUiEvent
    data object OnGoBack: DetailUiEvent
}