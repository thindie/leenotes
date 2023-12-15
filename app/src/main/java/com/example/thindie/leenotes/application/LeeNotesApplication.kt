package com.example.thindie.leenotes.application

import android.app.Application
import com.example.thindie.leenotes.application.di.AppComponent
import com.example.thindie.leenotes.common.di.App

class LeeNotesApplication: Application(), App {
    private lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        onProvideAppComponent()
    }
    private fun onProvideAppComponent() {
        if (::appComponent.isInitialized.not()){
            appComponent = AppComponent.init(this)
        }
    }

    override fun getAppComponent() = appComponent


}