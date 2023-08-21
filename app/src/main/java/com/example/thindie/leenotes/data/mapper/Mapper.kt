package com.example.thindie.leenotes.data.mapper

import com.example.thindie.leenotes.data.database.NotesEntity
import com.example.thindie.leenotes.domain.Note

fun Note.toNotesEntity(): NotesEntity {
    return NotesEntity(
        title = title,
        body = body,
        cost = cost,
        tagShadow = tagShadow,
        hyperLink = hyperLink,
        timestamp = timeStamp,
        createdAt = createdAt
    )
}

fun NotesEntity.toNote(): Note {
    return Note.getRefurbish(
        title = title,
        body = body,
        cost = cost,
        tagShadow = tagShadow,
        hyperLink = hyperLink,
        createdAt = createdAt,
        timeStamp = timestamp,
    )
}