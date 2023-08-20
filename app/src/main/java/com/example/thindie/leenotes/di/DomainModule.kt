package com.example.thindie.leenotes.di

import com.example.thindie.leenotes.data.RepositoryImpl
import com.example.thindie.leenotes.domain.LeeNotesInteractor
import com.example.thindie.leenotes.domain.NoteManager
import com.example.thindie.leenotes.domain.NotesObserver
import com.example.thindie.leenotes.domain.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
     @Binds
    fun bindRepository(impl: RepositoryImpl): NotesRepository

    @Binds
    fun bindObserver(interactor: LeeNotesInteractor): NotesObserver

    @Binds
    fun bindManager(interactor: LeeNotesInteractor): NoteManager
}