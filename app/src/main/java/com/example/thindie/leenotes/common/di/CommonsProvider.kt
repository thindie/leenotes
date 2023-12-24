package com.example.thindie.leenotes.common.di

import com.example.thindie.leenotes.common.di.dispatchers.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher

interface CommonsProvider {
    @IODispatcher
    fun provideCoroutineDispatcher(): CoroutineDispatcher
}