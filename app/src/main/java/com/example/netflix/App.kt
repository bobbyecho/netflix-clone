package com.example.netflix

import android.app.Application
import com.example.home.di.HomeModules
import com.example.login.di.LoginModules
import com.example.netflix.di.AppModules
import com.example.registration.di.RegisterModules
import com.example.shared.di.SharedModules
import com.example.splashscreen.di.SplashScreenModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                AppModules.getModules() +
                        SharedModules.getModules() +
                        SplashScreenModules.getModules() +
                        LoginModules.getModules() +
                        RegisterModules.getModules() +
                        HomeModules.getModules()
            )
        }
    }
}