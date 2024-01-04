package com.example.thindie.leenotes.data.di

import com.example.thindie.leenotes.data.NoteProviderImpl
import com.example.thindie.leenotes.domain.NoteProvider
import com.example.thindie.leenotes.presentation.features.feature_note.di.ConcreteNoteScope
import dagger.Binds
import dagger.Module

@Module
interface NoteProviderModule {
    @Binds
    @ConcreteNoteScope
    fun bindNoteProvider(impl: NoteProviderImpl): NoteProvider
}