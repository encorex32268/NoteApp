package com.lihan.noteapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteRoomDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
}