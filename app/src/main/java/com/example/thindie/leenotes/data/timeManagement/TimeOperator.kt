package com.example.thindie.leenotes.data.timeManagement

import java.time.LocalTime

interface TimeOperator {
    fun getCurrent(timeInMillis: Long): String
    fun getCurrent(timeInMillis: Long, pattern: String): String

    fun getCurrentLocalDateTime(timeInMillis: Long): LocalTime
}