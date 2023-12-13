package com.example.thindie.leenotes.application

import android.app.Application
import com.example.thindie.leenotes.application.di.AppComponent

class LeeNotesApplication: Application(), ComponentCoordinator{
    private lateinit var appComponent: AppComponent


    override fun provideAppComponent(receiver: ComponentReceiver) {
        if (::appComponent.isInitialized.not()){
            appComponent = AppComponent.init(this)
        }
        receiver.setAppComponent(appComponent)
    }


}