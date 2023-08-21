package com.example.thindie.leenotes.ui.common

import android.util.Log
import com.example.thindie.leenotes.domain.Note

fun String.matchCriteria(criteria: String): Boolean {
    if (this.isBlank() && criteria.isNotBlank()) return false
    return lowercase().contains(criteria.lowercase()) ||
            lowercase() == criteria.lowercase() ||
            criteria.lowercase().contains(this.lowercase())
}

fun List<Note>.searching(searching: String): List<Note> {
    return filter {
        it.title.matchCriteria(searching) ||
                it.tagShadow.matchCriteria(searching)
    }
}