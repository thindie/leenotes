package com.example.thindie.leenotes.common.di

import com.example.thindie.leenotes.data.database.di.LocalSourceProvider

interface DependenciesProvider: LocalSourceProvider {
    companion object {
        fun get(componentProvider: App): DependenciesProvider {
            return componentProvider.getAppComponent()
        }
    }
}