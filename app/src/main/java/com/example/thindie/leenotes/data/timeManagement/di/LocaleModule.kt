package com.example.thindie.leenotes.data.timeManagement.di

import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
internal class LocaleModule {
    @Provides
    @Singleton
    fun provideLocale(): Locale {
        return Locale.getDefault()
    }
}