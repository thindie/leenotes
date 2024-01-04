package com.example.thindie.leenotes.domain.entities

data class NoteBindings(val id: Int, val properties: String) {


    companion object {
        private const val prefix = "https://"

        fun buildProperties(properties: String): String {
            return if (!properties.contains(prefix)) {
                prefix.plus(properties)
            } else properties
        }
    }
}