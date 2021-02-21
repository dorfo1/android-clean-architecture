package com.example.koinsample

import android.app.Application
import com.example.koinsample.di.AppModule
import com.example.koinsample.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SampleApplication)
            modules(
                listOf(
                    NetworkModule.dependencyModules,
                    AppModule.dependencyModules
                )
            )
        }
    }
}