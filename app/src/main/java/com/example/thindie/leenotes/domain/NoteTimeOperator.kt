package com.example.thindie.leenotes.domain

interface NoteTimeOperator {
    fun getNoteTime(millis: Long): String

    fun getMonth(): String

    fun getYear(): String

    fun isCurrentMonth(millis: Long, timeOffset: Int): Boolean
    fun isCurrentYear(millis: Long, timeOffset: Int): Boolean

    fun isCurrentDay(millis: Long, timeOffset: Int): Boolean
}