package com.example.thindie.leenotes.presentation.features.feature_notes.di

import androidx.lifecycle.ViewModel
import com.example.thindie.leenotes.common.di.viewmodels_factory.ViewModelKey
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.viewodel.NotesScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module

interface NotesScreenViewModelModule {
    @Binds
    @NoteFeatureScope
    @IntoMap
    @ViewModelKey(NotesScreenViewModel::class)
    fun provideNotesScreenViewModel(notesScreenViewModel: NotesScreenViewModel): ViewModel


}