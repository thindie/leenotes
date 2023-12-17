package com.example.thindie.leenotes.data.di

import com.example.thindie.leenotes.data.NotesRepositoryImpl
import com.example.thindie.leenotes.domain.NotesRepository
import com.example.thindie.leenotes.presentation.features.feature_notes.di.NoteFeatureScope
import dagger.Binds
import dagger.Module

@Module
interface NotesRepositoryModule {
    @Binds
    @NoteFeatureScope
    fun bindNotesRepository(impl: NotesRepositoryImpl): NotesRepository
}