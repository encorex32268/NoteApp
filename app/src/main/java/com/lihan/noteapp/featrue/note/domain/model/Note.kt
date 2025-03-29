package com.lihan.noteapp.featrue.note.domain.model

data class Note(
    val id: Int?=null,
    val title: String,
    val description: String,
    val color: Long,
    val timestamp: Long
)
