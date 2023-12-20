package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.di

import com.example.thindie.leenotes.HandleShareIntentActivity
import com.example.thindie.leenotes.common.di.DependenciesProvider
import com.example.thindie.leenotes.data.di.HandleIntentRepositoryModule
import com.example.thindie.leenotes.presentation.features.feature_notes.di.ViewModelFactoryModule
import dagger.Component

@HandleScope
@Component(
    dependencies = [DependenciesProvider::class],
    modules = [
        ViewModelFactoryModule::class,
        HandleViewModelModule::class,
        HandleIntentRepositoryModule::class]
)
interface HandleFeatureComponent {
    companion object {
        fun init(dependenciesProvider: DependenciesProvider): HandleFeatureComponent {
            return DaggerHandleFeatureComponent.factory().create(dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(dependenciesProvider: DependenciesProvider): HandleFeatureComponent
    }

    fun inject(handleIntentActivity: HandleShareIntentActivity)
}