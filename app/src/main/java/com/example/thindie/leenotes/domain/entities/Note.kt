package com.example.thindie.leenotes.domain.entities

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val creationTimeInMillis: Long,
    val cost: Cost? = null,
    val bindings: NoteBindings? = null,
) {
    val isSpendable: Boolean get() = cost?.isBought == false && cost.price > 0.0

    val hasProperties: Boolean get() = bindings != null

    val isSpent: Boolean get() = cost?.isBought == true

    val isTemp: Boolean get() = cost == null && bindings == null
}