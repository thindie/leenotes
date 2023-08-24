package com.example.thindie.leenotes.domain

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject
private const val TIME_PATTERN = "MMM"
private const val MONTH_EPOCHMILLI = 2592000
class DateFilterHelper @Inject constructor() {

   private val monthList = mutableListOf<String>()

    init {
        repeat(2){
            val instant = Instant.now()
            val timeStamp = instant.epochSecond - (MONTH_EPOCHMILLI * it)
            val correctedInstant = Instant.ofEpochSecond(timeStamp)
            val localDateTime = LocalDateTime.ofInstant(correctedInstant, ZoneOffset.UTC)
            val createdAt = DateTimeFormatter.ofPattern(TIME_PATTERN).format(localDateTime)
            monthList.add(createdAt)
        }
    }

    fun getCurrentMonth(): String {
        return monthList[0]
    }

    fun getPrevMonth(): String = monthList[1]
}