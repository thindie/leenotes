package com.example.thindie.leenotes.data.timeManagement

import java.time.LocalDateTime

interface TimeOperator {
    fun getCurrent(timeInMillis: Long): String
    fun getCurrent(timeInMillis: Long, pattern: String): String

    fun getCurrentLocalDateTime(timeInMillis: Long): LocalDateTime

    fun getCurrent(localDateTime: LocalDateTime, pattern: String): String

    fun getCurrent(): LocalDateTime
}