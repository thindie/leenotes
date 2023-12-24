package com.example.thindie.leenotes.presentation.common

fun String.matchCriteria(criteria: String): Boolean {
    if (this.isBlank() && criteria.isNotBlank()) return false
    return lowercase().contains(criteria.lowercase()) ||
            lowercase() == criteria.lowercase() ||
            criteria.lowercase().contains(this.lowercase())
}