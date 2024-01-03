package com.example.thindie.leenotes.data.timeManagement.repository

import com.example.thindie.leenotes.data.timeManagement.TimeOperator
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeOperatorImpl @Inject constructor() : TimeOperator {
    override fun getCurrent(timeInMillis: Long): String {
        TODO("Not yet implemented")
    }

    override fun getCurrent(timeInMillis: Long, pattern: String): String {
        TODO("Not yet implemented")
    }

    override fun getCurrentLocalDateTime(timeInMillis: Long): LocalTime {
        TODO("Not yet implemented")
    }

}