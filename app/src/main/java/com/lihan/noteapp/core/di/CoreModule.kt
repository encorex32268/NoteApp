package com.lihan.noteapp.core.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.lihan.noteapp.core.data.local.NoteDao
import com.lihan.noteapp.core.data.local.NoteRoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {

    single{
        Room.databaseBuilder(
            context = androidContext(),
            klass = NoteRoomDatabase::class.java,
            name = "note_db"
        ).build()
    }.bind<NoteRoomDatabase>()

    single { get<NoteRoomDatabase>().noteDao() }.bind<NoteDao>()
}