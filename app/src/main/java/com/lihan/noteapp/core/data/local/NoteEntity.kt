package com.lihan.noteapp.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val title: String,
    val description: String,
    val color: Int,
    val timestamp: Long
)
