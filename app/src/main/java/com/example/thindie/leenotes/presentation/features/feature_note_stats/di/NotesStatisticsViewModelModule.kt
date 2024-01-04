package com.example.thindie.leenotes.presentation.features.feature_note_stats.di

import androidx.lifecycle.ViewModel
import com.example.thindie.leenotes.common.di.viewmodels_factory.ViewModelKey
import com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel.NotesStatisticsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface NotesStatisticsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NotesStatisticsViewModel::class)
    @NotesStatisticsScope
    fun bindNotesStatsViewModel(viewModel: NotesStatisticsViewModel): ViewModel
}