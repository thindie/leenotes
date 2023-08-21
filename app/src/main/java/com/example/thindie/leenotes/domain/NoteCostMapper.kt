package com.example.thindie.leenotes.domain

fun Note.map(): Cost {
    val day = createdAt.substringBefore(" ").trim()
    val month = createdAt.substringAfter(day).trim().substringBefore(" ")
    val year = createdAt.substringAfterLast(" ")

    return Cost(
        title = title,
        timeStamp = timeStamp,
        day = day,
        month = month,
        year = year,
        cost = cost
    )
}