package com.example.thindie.leenotes.data.mapper

import com.example.thindie.leenotes.data.database.entities.BindingsDbModel
import com.example.thindie.leenotes.data.database.entities.CostDbModel
import com.example.thindie.leenotes.data.database.entities.NoteDbModel
import com.example.thindie.leenotes.domain.entities.Cost
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.domain.entities.NoteBindings

suspend fun NoteDbModel
    .map(bindingGet: suspend (Int?) -> BindingsDbModel?, costGet: suspend (Int?) -> CostDbModel?): Note {
    return Note(
        id = id,
        title = title,
        description = description,
        creationTimeInMillis = creationTimeInMillis,
        bindings = bindingGet(bindingsId)?.toNoteBindings(),
        cost = costGet(costId)?.toCost()
    )
}

fun BindingsDbModel?.toNoteBindings(): NoteBindings? {
    return if (this != null) {
        NoteBindings(id, properties)
    }
    else null
}

fun CostDbModel?.toCost(): Cost? {
    return if (this != null) {
        Cost(id, price)
    }
    else null
}