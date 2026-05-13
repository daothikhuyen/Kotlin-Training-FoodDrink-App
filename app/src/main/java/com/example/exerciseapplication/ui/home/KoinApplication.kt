package com.example.exerciseapplication.ui.home

import android.app.Application
import com.example.exerciseapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger() // log ra logcat
            androidContext(this@KoinApplication) // truyền context
            modules(appModule) // register dependency
        }
    }
}