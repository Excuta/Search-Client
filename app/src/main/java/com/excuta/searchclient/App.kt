package com.excuta.searchclient

import android.app.Application
import com.excuta.searchclient.domain.di.domainModule
import com.excuta.searchclient.presentation.di.presentationModule
import com.excuta.searchclient.ui.main.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@App)
            modules(listOf(domainModule, presentationModule, searchModule))
        }

    }
}