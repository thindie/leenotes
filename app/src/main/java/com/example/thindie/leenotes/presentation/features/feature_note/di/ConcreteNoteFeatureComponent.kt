package com.example.thindie.leenotes.presentation.features.feature_note.di

import androidx.lifecycle.ViewModelProvider
import com.example.thindie.leenotes.common.di.DependenciesProvider
import com.example.thindie.leenotes.data.di.NoteProviderModule
import com.example.thindie.leenotes.data.di.NoteTimeOperatorModule
import com.example.thindie.leenotes.presentation.features.feature_notes.di.ViewModelFactoryModule
import dagger.Component

@ConcreteNoteScope
@Component(
    dependencies = [DependenciesProvider::class],
    modules = [ViewModelFactoryModule::class,
        ConcreteNoteViewModelModule::class,
        NoteProviderModule::class,
        NoteTimeOperatorModule::class
    ]
)
interface ConcreteNoteFeatureComponent {
    companion object {
        fun init(dependenciesProvider: DependenciesProvider): ConcreteNoteFeatureComponent {
            return DaggerConcreteNoteFeatureComponent.factory().create(dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(dependenciesProvider: DependenciesProvider): ConcreteNoteFeatureComponent
    }

    fun provideFactory(): ViewModelProvider.Factory
}