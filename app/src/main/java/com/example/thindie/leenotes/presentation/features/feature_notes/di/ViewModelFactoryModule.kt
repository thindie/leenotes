package com.example.thindie.leenotes.presentation.features.feature_notes.di

import androidx.lifecycle.ViewModelProvider
import com.example.thindie.leenotes.common.di.viewmodels_factory.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
interface ViewModelFactoryModule{
    @Binds
     fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}