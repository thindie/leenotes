package com.example.thindie.leenotes.application.di

import android.content.Context
import com.example.thindie.leenotes.common.di.CommonsProvider
import com.example.thindie.leenotes.common.di.DependenciesProvider
import com.example.thindie.leenotes.common.di.component.CommonsComponent
import com.example.thindie.leenotes.data.database.di.LocalSourceComponent
import com.example.thindie.leenotes.data.database.di.LocalSourceProvider
import com.example.thindie.leenotes.data.timeManagement.TimeOperatorProvider
import com.example.thindie.leenotes.data.timeManagement.di.TimeManagementComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [LocalSourceProvider::class, CommonsProvider::class, TimeOperatorProvider::class])
interface AppComponent : DependenciesProvider {

    companion object {
        fun init(context: Context): AppComponent {
            val localSource = LocalSourceComponent.init(context)
            val commons = CommonsComponent.init()
            val time = TimeManagementComponent.init()
            return DaggerAppComponent
                .factory()
                .create(localSource, commons, time)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            localSourceProvider: LocalSourceProvider,
            commonsProvider: CommonsProvider,
            timeOperatorProvider: TimeOperatorProvider
        ): AppComponent
    }

}