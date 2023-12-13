package com.example.thindie.leenotes.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.thindie.leenotes.data.database.NotesAppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataBaseModule {
    private const val DB_NAME = "notes"

    @Provides
    @Singleton
    fun provideDataBase(context: Context): NotesAppDataBase {
        return Room.databaseBuilder(
            context = context, klass = NotesAppDataBase::class.java, name = DB_NAME
        ).build()
    }
}