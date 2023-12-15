package com.example.thindie.leenotes.common.di

import com.example.thindie.leenotes.application.di.AppComponent

interface App {
    fun getAppComponent(): AppComponent
}