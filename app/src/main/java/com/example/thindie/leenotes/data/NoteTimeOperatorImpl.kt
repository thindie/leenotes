package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.data.timeManagement.TimeOperator
import com.example.thindie.leenotes.domain.NoteTimeOperator
import com.example.thindie.leenotes.presentation.features.feature_note.di.ConcreteNoteScope
import javax.inject.Inject

@ConcreteNoteScope
class NoteTimeOperatorImpl @Inject constructor(private val operator: TimeOperator) :
    NoteTimeOperator {
    override fun getNoteTime(millis: Long): String {
        return operator.getCurrent(millis)
    }

    override fun getMonth(): String {
        return operator.getCurrent().month.toString()
    }

    override fun getYear(): String {
        return operator.getCurrent().year.toString()
    }

}