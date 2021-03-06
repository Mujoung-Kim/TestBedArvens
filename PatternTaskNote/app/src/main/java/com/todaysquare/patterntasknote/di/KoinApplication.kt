package com.todaysquare.patterntasknote.di

import android.app.Application

import com.todaysquare.patterntasknote.BuildConfig
import com.todaysquare.patterntasknote.di.modules.*

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
                androidLogger(Level.DEBUG)

            androidContext(this@KoinApplication)
            modules(repositoryModule, localDataModule, remoteDataModule, viewModelModule, apiModule)

        }
    }
}