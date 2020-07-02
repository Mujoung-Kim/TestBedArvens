package com.todaysquare.koinmovieexample.base

import android.app.Application

import com.todaysquare.koinmovieexample.di.modules.movieModule
import com.todaysquare.koinmovieexample.di.modules.picassoModule
import com.todaysquare.koinmovieexample.di.modules.retrofitModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(retrofitModule, picassoModule, movieModule))

        }
    }
}