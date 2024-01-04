package com.example.thindie.leenotes.presentation.features.feature_note.di

import androidx.lifecycle.ViewModel
import com.example.thindie.leenotes.common.di.viewmodels_factory.ViewModelKey
import com.example.thindie.leenotes.presentation.features.feature_note.viewmodel.ConcreteNoteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ConcreteNoteViewModelModule {
    @ConcreteNoteScope
    @Binds
    @IntoMap
    @ViewModelKey(ConcreteNoteViewModel::class)
    fun bindViewModel(concreteNoteViewModel: ConcreteNoteViewModel): ViewModel
}