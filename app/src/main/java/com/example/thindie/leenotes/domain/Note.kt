package com.example.thindie.leenotes.domain

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


data class Note(
    val title: String,
    val body: String = DEFAULT_STRING_FIELD,
    val isHasBody: Boolean = body.isNotBlank(),
    val cost: Int = 0,
    val isCost: Boolean = cost != 0,
    val tagShadow: String = DEFAULT_STRING_FIELD,
    val isSearchAdapted: Boolean = tagShadow.isNotBlank(),
    val hyperLink: String = DEFAULT_STRING_FIELD,
    val isHasLink: Boolean = tagShadow.isNotBlank(),
    val createdAt: String = DEFAULT_STRING_FIELD,
) {
    private var instant: Instant? = null
    private var timestamp: Long? = instant?.epochSecond
    private var localDateTime: LocalDateTime? = null
    private var noteCreateAt: String? = null
    fun getInstant() = this.instant
    fun getTimeStamp() = this.timestamp ?: 0L

    fun setTimeStamp(timeStamp: Long) {
        if (timestamp == 0L){
           this.timestamp = timeStamp
        }
    }

    fun setCreatedDate(instant: Instant?): Note {
        if (this.instant == null && instant == null && createdAt.isBlank()) {
            this.instant = Instant.now()
            this.timestamp = this.instant?.epochSecond
            this.localDateTime = LocalDateTime
                .ofInstant(this.instant, ZoneOffset.UTC)
            this.noteCreateAt = DateTimeFormatter.ofPattern(TIME_PATTERN).format(localDateTime)
        } else if (this.instant == null && instant != null && createdAt.isBlank()) {
            this.instant = instant
            this.timestamp = this.instant?.epochSecond
            this.localDateTime = LocalDateTime
                .ofInstant(this.instant, ZoneOffset.UTC)
            this.noteCreateAt = DateTimeFormatter.ofPattern(TIME_PATTERN).format(localDateTime)
        }
        return Note(
            title = this.title,
            body = this.body,
            cost = this.cost,
            tagShadow = this.tagShadow,
            hyperLink = this.hyperLink,
            createdAt = this.createdAt.ifBlank { this.noteCreateAt!! }
        )
    }


    fun noteUpdater(body: String, cost: Int, tagShadow: String, hyperLink: String): Note {
        return Note(
            title = this.title,
            body = body,
            cost = cost,
            isCost = false,
            tagShadow = tagShadow,
            hyperLink = hyperLink,
            createdAt = this.createdAt
        ).setCreatedDate(this.instant)
    }


    companion object {
        private const val TIME_PATTERN = "d MMM yyyy"
        private const val DEFAULT_STRING_FIELD = ""

        fun getInstance(title: String, body: String = DEFAULT_STRING_FIELD): Note {
            val note = Note(title = title, body = body)
            return note.setCreatedDate(null)
        }
    }
}