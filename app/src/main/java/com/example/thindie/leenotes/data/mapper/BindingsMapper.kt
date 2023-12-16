package com.example.thindie.leenotes.data.mapper

import com.example.thindie.leenotes.data.database.entities.BindingsDbModel
import com.example.thindie.leenotes.domain.entities.NoteBindings

fun NoteBindings.toBindingsDbModel(): BindingsDbModel {
   return BindingsDbModel(id,properties)
}