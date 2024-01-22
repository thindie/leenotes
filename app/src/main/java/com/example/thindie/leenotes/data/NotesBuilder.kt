package com.example.thindie.leenotes.data

import androidx.core.text.isDigitsOnly
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
            cost = tryFindCosts(stringsSplit),
            bindings = binding,
            title = title.capitalize(),
            id = 0,
            description = "",
            creationTimeInMillis = System.currentTimeMillis()
        )
        resetInitialValues()

        return builtNote
    }

    private fun String.capitalize() = try {
        replaceFirstChar { it.uppercase() }
    } catch (_: Exception) {
        ""
    }


    private fun tryFindCosts(list: List<String>): Cost? {
        var price: String = ""
        list
            .forEach {
                if (it.isDigitsOnly()) price = it
                else title = title
                    .plus(it)
                    .plus(" ")
            }
        title.trim()
        return price.toRawCost()
    }

    private fun String?.toRawCost(): Cost? {
        return if (this != null) {
            try {
                Cost(price = this.toDouble(), isBought = false)
            } catch (_: Exception) {
                null
            }
        } else null
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