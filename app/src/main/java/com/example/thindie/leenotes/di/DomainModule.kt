package com.example.thindie.leenotes.di

import com.example.thindie.leenotes.data.CostRepositoryImpl
import com.example.thindie.leenotes.data.RepositoryImpl
import com.example.thindie.leenotes.domain.CostManager
import com.example.thindie.leenotes.domain.CostRepository
import com.example.thindie.leenotes.domain.CostsInteractor
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

    @Binds
    fun bindCostRepository(impl: CostRepositoryImpl): CostRepository


    @Binds
    fun bindCostManager(interactor: CostsInteractor): CostManager
}