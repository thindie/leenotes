package com.example.thindie.leenotes.data.di

import com.example.thindie.leenotes.data.SummaryRepositoryImpl
import com.example.thindie.leenotes.domain.SummaryRepository
import com.example.thindie.leenotes.presentation.features.feature_note_stats.di.NotesStatisticsScope
import dagger.Binds
import dagger.Module

@Module
interface SummaryRepositoryModule {
    @NotesStatisticsScope
    @Binds
    fun bindSummaryRepository(impl: SummaryRepositoryImpl): SummaryRepository
}