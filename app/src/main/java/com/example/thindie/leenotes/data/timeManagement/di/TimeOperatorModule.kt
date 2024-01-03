package com.example.thindie.leenotes.data.timeManagement.di

import com.example.thindie.leenotes.data.timeManagement.TimeOperator
import com.example.thindie.leenotes.data.timeManagement.repository.TimeOperatorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface TimeOperatorModule {
    @Binds
    @Singleton
    fun bindTimeOperatorProvider(impl: TimeOperatorImpl): TimeOperator
}