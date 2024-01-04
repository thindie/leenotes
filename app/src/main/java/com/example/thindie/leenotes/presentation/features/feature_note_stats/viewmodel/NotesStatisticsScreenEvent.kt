package com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel

sealed class NotesStatisticsScreenEvent {
    data object Forward: NotesStatisticsScreenEvent()
    data object BackWard: NotesStatisticsScreenEvent()

    data object Month: NotesStatisticsScreenEvent()
    data object Year: NotesStatisticsScreenEvent()
    data object Day: NotesStatisticsScreenEvent()
}