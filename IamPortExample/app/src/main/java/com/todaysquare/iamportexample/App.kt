package com.todaysquare.iamportexample

import android.app.Application
//import com.todaysquare.iamportexample.data.databases.AppDatabase

import com.todaysquare.iamportexample.data.network.ApiService
import com.todaysquare.iamportexample.data.network.NetworkConnectionInterceptor
import com.todaysquare.iamportexample.data.preferences.PreferenceProvider
import com.todaysquare.iamportexample.data.repositories.MainRepository
import com.todaysquare.iamportexample.ui.main.MainViewModelFactory
//import com.todaysquare.iamportexample.data.repositories.TokenRepository

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { MainRepository(instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
//        bind() from singleton { AppDatabase(instance()) }
//        bind() from singleton { TokenRepository(instance(), instance()) }
//        bind() from provider { TokenViewModelFactory(instance()) }

    }
}