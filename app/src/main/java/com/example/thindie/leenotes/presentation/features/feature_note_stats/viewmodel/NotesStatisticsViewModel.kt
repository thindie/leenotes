package com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.common.di.dispatchers.IODispatcher
import com.example.thindie.leenotes.domain.SummaryStep
import com.example.thindie.leenotes.domain.usecase.GetAllTimeSummaryUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryDayStepSetUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryMonthStepSetUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryStepBackUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryStepForwardUseCase
import com.example.thindie.leenotes.domain.usecase.SummaryYearStepSetUseCase
import com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel.NotesStatisticsScreenEvent.*
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

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


    private val _summaryStep = MutableStateFlow(SummaryStep.MONTH)

    val screenState = _summaryStep.combine(getAllTimeSummaryUseCase()) { step, summary ->
        NotesStatisticsScreenState(step, summary)
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            NotesStatisticsScreenState()
        )

    fun onEvent(event: NotesStatisticsScreenEvent) {
        when (event) {
            BackWard -> {

            }

            Day -> {

            }

            Forward -> {

            }

            Month -> {

            }

            Year -> {

            }
        }
    }
}