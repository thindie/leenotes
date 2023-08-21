package com.example.thindie.leenotes.data.mapper

import com.example.thindie.leenotes.data.database.CostDbModel
import com.example.thindie.leenotes.data.database.NoteDbModel
import com.example.thindie.leenotes.domain.Cost
import com.example.thindie.leenotes.domain.Note

fun Note.toNotesEntity(): NoteDbModel {
    return NoteDbModel(
        title = title,
        body = body,
        cost = cost,
        tagShadow = tagShadow,
        hyperLink = hyperLink,
        timestamp = timeStamp,
        createdAt = createdAt
    )
}

fun NoteDbModel.toNote(): Note {
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

fun Cost.toCostDbModel(): CostDbModel {
    return CostDbModel(
        title = title,
        timeStamp = timeStamp,
        day = day,
        month = month,
        year = year,
        cost = cost
    )
}

fun CostDbModel.toCost(): Cost {
    return Cost(
        title = title,
        timeStamp = timeStamp,
        day = day,
        month = month,
        year = year,
        cost = cost
    )
}