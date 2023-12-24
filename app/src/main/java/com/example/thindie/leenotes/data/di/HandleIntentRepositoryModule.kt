package com.example.thindie.leenotes.data.di

import com.example.thindie.leenotes.data.HandleIntentNotesRepositoryImpl
import com.example.thindie.leenotes.domain.NotesRepository
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.di.HandleScope
import dagger.Binds
import dagger.Module

@Module
interface HandleIntentRepositoryModule {

    @Binds
    @HandleScope
    fun bindHandleIntentRepository(impl: HandleIntentNotesRepositoryImpl): NotesRepository
}