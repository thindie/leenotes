package com.example.thindie.leenotes.application

import com.example.thindie.leenotes.application.di.AppComponent

interface ComponentReceiver {
    fun setAppComponent(appComponent: AppComponent)
}
