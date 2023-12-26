package com.example.thindie.leenotes.presentation.features.feature_note.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class ConcreteNoteScope
