package com.example.thindie.leenotes.common.di

import com.example.thindie.leenotes.data.database.di.LocalSourceProvider
import com.example.thindie.leenotes.data.timeManagement.TimeOperatorProvider

interface DependenciesProvider : LocalSourceProvider, CommonsProvider, TimeOperatorProvider {
    companion object {
        fun get(componentProvider: App): DependenciesProvider {
            return componentProvider.getAppComponent()
        }
    }
}