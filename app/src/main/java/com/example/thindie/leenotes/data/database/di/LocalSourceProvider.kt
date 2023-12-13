package com.example.thindie.leenotes.data.database.di

import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesDao

interface LocalSourceProvider {
    fun provideCostsDao(): CostsDao

    fun provideNotesDao(): NotesDao
}