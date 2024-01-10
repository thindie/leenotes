package com.example.thindie.leenotes.domain

interface NoteTimeOperator {
    fun getNoteTime(millis: Long): String

    fun getMonth(): String

    fun getYear(): String
}