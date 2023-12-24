package com.example.thindie.leenotes.common.di.component

import com.example.thindie.leenotes.common.di.CommonsProvider
import com.example.thindie.leenotes.common.di.dispatchers.DispatchersModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DispatchersModule::class])
interface CommonsComponent: CommonsProvider {
    companion object{
        fun init(): CommonsComponent {
            return DaggerCommonsComponent
                .factory()
                .create()

        }
    }

    @Component.Factory
    interface Factory{
        fun create(): CommonsComponent
    }


}