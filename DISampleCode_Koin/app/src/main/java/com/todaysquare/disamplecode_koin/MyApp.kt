package com.todaysquare.disamplecode_koin

import android.app.Application

import com.todaysquare.disamplecode_koin.utils.exampleModule
import com.todaysquare.disamplecode_koin.utils.koinViewModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
//            modules(listOf(appModule))
            modules(exampleModule, koinViewModule)

        }
    }
}