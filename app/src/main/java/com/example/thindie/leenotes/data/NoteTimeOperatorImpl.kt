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

    override fun isCurrentMonth(millis: Long, timeOffset: Int): Boolean {
        val currentMonth = operator.getCurrent().minusMonths(timeOffset.toLong())
        val millisMonth = operator.getCurrentLocalDateTime(millis)
        return currentMonth.month == millisMonth.month
    }

    override fun isCurrentYear(millis: Long, timeOffset: Int): Boolean {
        val currentMonth = operator.getCurrent().minusYears(timeOffset.toLong())
        val millisMonth = operator.getCurrentLocalDateTime(millis)
        return currentMonth.year == millisMonth.year
    }

    override fun isCurrentDay(millis: Long, timeOffset: Int): Boolean {
        val currentMonth = operator.getCurrent().minusDays(timeOffset.toLong())
        val millisMonth = operator.getCurrentLocalDateTime(millis)
        return currentMonth.dayOfMonth == millisMonth.dayOfMonth
    }
}