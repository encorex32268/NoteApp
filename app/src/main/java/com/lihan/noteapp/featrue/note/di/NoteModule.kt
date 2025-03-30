package com.lihan.noteapp.featrue.note.di

import com.lihan.noteapp.featrue.note.data.repository.NoteDetailRepositoryImpl
import com.lihan.noteapp.featrue.note.data.repository.NotesRepositoryImpl
import com.lihan.noteapp.featrue.note.domain.repository.NoteDetailRepository
import com.lihan.noteapp.featrue.note.domain.repository.NotesRepository
import com.lihan.noteapp.featrue.note.presentation.NotesViewModel
import com.lihan.noteapp.featrue.note.presentation.detail.DetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


val noteModule = module {
    singleOf(::NotesRepositoryImpl).bind<NotesRepository>()
    singleOf(::NoteDetailRepositoryImpl).bind<NoteDetailRepository>()

    viewModelOf(::NotesViewModel)
    viewModelOf(::DetailViewModel)
}