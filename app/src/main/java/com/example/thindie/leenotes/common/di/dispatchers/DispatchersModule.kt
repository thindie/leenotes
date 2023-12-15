package com.example.thindie.leenotes.common.di.dispatchers

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DispatchersModule {
    @Provides
    @Singleton
    @IODispatcher
    fun provideIoDispatchers(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}