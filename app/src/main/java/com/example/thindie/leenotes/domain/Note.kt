package com.example.thindie.leenotes.domain

import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


data class Note(
    val instant: Instant = Instant.now(),
    val title: String,
    val body: String = DEFAULT_STRING_FIELD,
    val isHasBody: Boolean = body.isNotBlank(),
    val cost: Int = 0,
    val isCost: Boolean = cost != 0,
    val tagShadow: String = DEFAULT_STRING_FIELD,
    val isSearchAdapted: Boolean = tagShadow.isNotBlank(),
    val hyperLink: String = DEFAULT_STRING_FIELD,
    val isHasLink: Boolean = tagShadow.isNotBlank(),
    val timestamp: Long = instant.epochSecond
) {

     private val localDateTime = LocalDateTime
        .ofInstant(instant, ZoneOffset.UTC)

    val noteCreateAt
        get() = DateTimeFormatter.ofPattern(TIME_PATTERN).format(localDateTime)

    companion object {
        private const val TIME_PATTERN = "d MMM yyyy"
        private const val DEFAULT_STRING_FIELD = ""
    }
}