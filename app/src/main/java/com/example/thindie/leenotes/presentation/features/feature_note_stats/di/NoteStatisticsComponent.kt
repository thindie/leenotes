package com.example.thindie.leenotes.presentation.features.feature_note_stats.di

import androidx.lifecycle.ViewModelProvider
import com.example.thindie.leenotes.common.di.DependenciesProvider
import com.example.thindie.leenotes.presentation.features.feature_notes.di.ViewModelFactoryModule
import dagger.Component

@NotesStatisticsScope
@Component(
    dependencies = [DependenciesProvider::class],
    modules = [NotesStatisticsViewModelModule::class, ViewModelFactoryModule::class]
)
interface NoteStatisticsComponent {
    companion object {
        fun init(dependenciesProvider: DependenciesProvider): NoteStatisticsComponent {
            return DaggerNoteStatisticsComponent
                .factory()
                .create(dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(dependenciesProvider: DependenciesProvider): NoteStatisticsComponent
    }


    fun getViewModelFactory(): ViewModelProvider.Factory
}