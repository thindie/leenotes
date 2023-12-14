package com.example.thindie.leenotes.application.di

import android.content.Context
import com.example.thindie.leenotes.MainActivity
import com.example.thindie.leenotes.data.database.di.LocalSourceComponent
import com.example.thindie.leenotes.data.database.di.LocalSourceProvider
import com.example.thindie.leenotes.presentation.features.feature_notes.di.NotesFeatureProvider
import com.example.thindie.leenotes.presentation.features.feature_notes.di.NotesScreenComponent
import dagger.Component

@Component(dependencies = [NotesFeatureProvider::class])
interface AppComponent {

    companion object{
        fun init(context: Context): AppComponent {
            val localSource = LocalSourceComponent.init(context)
            val notesFeature = NotesScreenComponent.init(localSource)
            return DaggerAppComponent
                .factory()
                .create(notesFeature)
        }
    }
    @Component.Factory
    interface  Factory{
        fun create(notesFeatureProvider: NotesFeatureProvider): AppComponent
    }

    fun inject(activity: MainActivity)
}