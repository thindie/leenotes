package com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel

import androidx.lifecycle.ViewModel
import com.example.thindie.leenotes.common.di.dispatchers.IODispatcher
import com.example.thindie.leenotes.domain.usecase.GetAllTimeSummaryUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryDayStepSetUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryMonthStepSetUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryStepBackUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryStepForwardUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryYearStepSetUseCase
import com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel.NotesStatisticsScreenEvent.*
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@Suppress("LongParameterList")
class NotesStatisticsViewModel @Inject constructor(
    private val getAllTimeSummaryUseCase: GetAllTimeSummaryUseCase,
    private val setSummaryDayStep: SummaryDayStepSetUseCase,
    private val setSummaryMonthStep: SummaryMonthStepSetUseCase,
    private val setSummaryYearStep: SummaryYearStepSetUseCase,
    private val stepBackUseCase: SummaryStepBackUseCase,
    private val stepForwardUseCase: SummaryStepForwardUseCase,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {




    fun onEvent(event: NotesStatisticsScreenEvent) {
        when (event) {
            BackWard -> TODO()
            Day -> TODO()
            Forward -> TODO()
            Month -> TODO()
            Year -> TODO()
        }
    }
}