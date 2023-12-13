package com.example.thindie.leenotes.data.di

import android.app.Application
import androidx.room.Room
import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesAppDataBase
import com.example.thindie.leenotes.data.database.NotesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataBaseModule {
    private const val DB_NAME = "notes"

    @Provides
    @Singleton
    fun provideDataBase(application: Application): NotesAppDataBase {
        return Room.databaseBuilder(
            context = application, klass = NotesAppDataBase::class.java, name = DB_NAME
        ).build()
    }
}