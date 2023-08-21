package com.example.thindie.leenotes.domain

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Suppress("LongParameterList")
class Note private constructor(
    val title: String,
    val body: String = DEFAULT_STRING_FIELD,
    val isHasBody: Boolean = body.isNotBlank(),
    val cost: Int = 0,
    val timeStamp: Long,
    val isCost: Boolean = cost != 0,
    val tagShadow: String = DEFAULT_STRING_FIELD,
    val isSearchAdapted: Boolean = tagShadow.isNotBlank(),
    val hyperLink: String = DEFAULT_STRING_FIELD,
    val isHasLink: Boolean = tagShadow.isNotBlank(),
    val createdAt: String,
) {


    fun noteUpdater(body: String, cost: Int, tagShadow: String, hyperLink: String): Note {
        return Note(
            title = this.title,
            body = body.ifBlank { this.body },
            cost = cost.ifZero { this.cost },
            tagShadow = tagShadow.ifBlank { this.tagShadow },
            hyperLink = hyperLink.ifBlank { this.hyperLink },
            timeStamp = this.timeStamp,
            createdAt = this.createdAt
        )
    }

    private fun Int.ifZero(defaultValue: () -> Int): Int {
        return if (this == 0) {
            if (defaultValue.invoke() > 0) {
                defaultValue.invoke()
            } else 0
        } else this
    }

    companion object {
        private const val TIME_PATTERN = "d MMM yyyy"
        private const val DEFAULT_STRING_FIELD = ""

        fun getInstance(title: String, body: String = DEFAULT_STRING_FIELD): Note {
            val instant = Instant.now()
            val timeStamp = instant.epochSecond
            val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
            val createdAt = DateTimeFormatter.ofPattern(TIME_PATTERN).format(localDateTime)
            return Note(
                title = title,
                body = body,
                timeStamp = timeStamp,
                createdAt = createdAt
            )
        }

        fun getRefurbish(
            title: String,
            body: String,
            cost: Int,
            tagShadow: String,
            hyperLink: String,
            createdAt: String,
            timeStamp: Long,
        ): Note {
            return Note(
                title = title,
                body = body,
                cost = cost,
                timeStamp = timeStamp,
                tagShadow = tagShadow,
                hyperLink = hyperLink,
                createdAt = createdAt
            )
        }
    }
}