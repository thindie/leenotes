package com.example.thindie.leenotes.data.timeManagement.di

import com.example.thindie.leenotes.data.timeManagement.TimeOperatorProvider
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [TimeOperatorModule::class,
        DateTimeFormatterModule::class,
        LocaleModule::class,
        TimeZoneModule::class
    ]
)
@Singleton
interface TimeManagementComponent : TimeOperatorProvider {
    companion object {
        fun init(): TimeManagementComponent {
            return DaggerTimeManagementComponent
                .factory()
                .create()
        }
    }

    @Component.Factory
    interface Factory {
        fun create(): TimeManagementComponent
    }
}