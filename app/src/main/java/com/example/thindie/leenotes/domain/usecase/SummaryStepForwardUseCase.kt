package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.SummaryRepository
import javax.inject.Inject

class SummaryStepForwardUseCase @Inject constructor(private val summaryRepository: SummaryRepository) {
    operator fun invoke() {
        summaryRepository.requestNextSummaryStep()
    }
}