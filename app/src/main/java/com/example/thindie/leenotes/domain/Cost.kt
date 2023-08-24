package com.example.thindie.leenotes.domain

data class Cost(
    val timeStamp: Long,
    val title: String,
    val day: String,
    val month: String,
    val year: String,
    val cost: Int,
) {
    fun getDate(): String {
        return day.plus(" $month").plus(" $year")
    }
}