package com.example.thindie.leenotes.domain.entities

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val creationTimeInMillis: Long,
    val cost: Cost? = null,
    val bindings: NoteBindings? = null,
) {
    val isSpendable: Boolean get() = cost != null

    val isFreeOfCharge: Boolean get() = cost == null

    val hasProperties: Boolean get() = bindings != null

    val hasNotProperties: Boolean get() = bindings == null
}