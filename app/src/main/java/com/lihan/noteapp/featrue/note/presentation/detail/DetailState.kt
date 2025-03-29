package com.lihan.noteapp.featrue.note.presentation.detail

data class DetailState(
    val noteId: Int?=null,
    val selectedColor: Int?= null,
    val colors: List<Long> = emptyList(),
    val title: String = "",
    val description: String = ""
)
