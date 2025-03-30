package com.lihan.noteapp

import android.app.Application
import com.lihan.noteapp.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NoteApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NoteApp)
            modules(
                listOf(
                    coreModule
                )
            )
        }
    }
}