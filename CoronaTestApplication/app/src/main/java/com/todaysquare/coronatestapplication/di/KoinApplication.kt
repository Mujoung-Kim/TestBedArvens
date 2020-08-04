package com.todaysquare.coronatestapplication.di

import android.app.Application

import com.todaysquare.coronatestapplication.BuildConfig
import com.todaysquare.coronatestapplication.di.modules.*

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger()
            else
                androidLogger(Level.NONE)

            androidContext(this@KoinApplication)
            modules(apiModule, viewModelModule, localDataModule, remoteModule,
                repositoryModule, networkModule)

        }
    }
}