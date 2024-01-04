package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.NoteTimeOperator
import javax.inject.Inject

class GetNoteTimeUseCase @Inject constructor(private val noteTimeOperator: NoteTimeOperator) {
     operator fun invoke(millis: Long): String {
        return noteTimeOperator.getNoteTime(millis)
    }

}