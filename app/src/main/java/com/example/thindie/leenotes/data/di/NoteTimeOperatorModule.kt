package com.example.thindie.leenotes.data.di

import com.example.thindie.leenotes.data.NoteTimeOperatorImpl
import com.example.thindie.leenotes.domain.NoteTimeOperator
import com.example.thindie.leenotes.presentation.features.feature_note.di.ConcreteNoteScope
import dagger.Binds
import dagger.Module


@Module
interface NoteTimeOperatorModule {
    @Binds
    @ConcreteNoteScope
    fun bindNoteTimeOperator(impl: NoteTimeOperatorImpl): NoteTimeOperator
}