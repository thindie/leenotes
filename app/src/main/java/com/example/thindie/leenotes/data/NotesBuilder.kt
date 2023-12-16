package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.domain.entities.Cost
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.domain.entities.NoteBindings

class NotesBuilder private constructor() {

    fun splitInitialString(): NotesBuilder {
        stringsSplit = initialString.orEmpty().split(" ")
        return this
    }

    fun build(): Note {
        val builtNote = Note(
            cost = cost,
            bindings = binding,
            title = title,
            id = 0,
            description = "",
            creationTimeInMillis = System.currentTimeMillis()
        )
        resetInitialValues()

        return builtNote
    }

    companion object {
        private var initialString: String? = null
        private var cost: Cost? = null
        private var title: String = ""
        private var binding: NoteBindings? = null
        private var stringsSplit = emptyList<String>()


        fun init(initialString: String?): NotesBuilder {
            Companion.initialString = initialString
            return NotesBuilder()
        }

        private fun resetInitialValues() {
            initialString = null
            cost = null
            title = ""
            binding = null
            stringsSplit = emptyList()
        }
    }
}