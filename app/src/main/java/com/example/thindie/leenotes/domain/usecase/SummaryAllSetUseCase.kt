package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.SummaryRepository
import com.example.thindie.leenotes.domain.SummaryStep
import javax.inject.Inject

class SummaryAllSetUseCase @Inject constructor(private val summaryRepository: SummaryRepository) {
    operator fun invoke() {
        summaryRepository.setSummaryStep(SummaryStep.ALL)
    }
}