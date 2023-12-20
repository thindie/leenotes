package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
annotation class HandleScope
