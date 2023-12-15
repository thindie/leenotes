package com.example.thindie.leenotes.presentation.features.feature_notes.di

import androidx.lifecycle.ViewModelProvider
import com.example.thindie.leenotes.common.di.DependenciesProvider
import dagger.Component

@Component(dependencies = [DependenciesProvider::class],
    modules = [NotesScreenViewModelModule::class, ViewModelFactoryModule::class])
@NoteFeatureScope
interface NotesScreenComponent {
    companion object{
        fun init(dependenciesProvider: DependenciesProvider): NotesScreenComponent{
            return DaggerNotesScreenComponent
                .factory()
                .create(dependenciesProvider)
        }
    }


    @Component.Factory
    interface Factory{
        fun create(dependenciesProvider: DependenciesProvider): NotesScreenComponent
    }

    fun provideFactory(): ViewModelProvider.Factory

}