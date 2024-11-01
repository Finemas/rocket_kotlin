package com.example.rocket

import android.app.Application
import com.example.rocket.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val context = this

        startKoin {
            modules(appModule)
            androidLogger()
            androidContext(context)
        }
    }
}
