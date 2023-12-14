package com.example.thindie.leenotes.presentation.features.feature_notes.di

import com.example.thindie.leenotes.data.database.di.LocalSourceProvider
import dagger.Component

@Component(dependencies = [LocalSourceProvider::class])
@NoteFeatureScope
interface NotesScreenComponent: NotesFeatureProvider {
    companion object{
        fun init(localSourceProvider: LocalSourceProvider): NotesScreenComponent{
            return DaggerNotesScreenComponent
                .factory()
                .create(localSourceProvider)
        }
    }


    @Component.Factory
    interface Factory{
        fun create(localSourceProvider: LocalSourceProvider): NotesScreenComponent
    }
}