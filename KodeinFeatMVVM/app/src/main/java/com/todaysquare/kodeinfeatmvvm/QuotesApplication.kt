package com.todaysquare.kodeinfeatmvvm

import android.app.Application

import com.todaysquare.kodeinfeatmvvm.data.databases.AppDatabase
import com.todaysquare.kodeinfeatmvvm.data.databases.AppDatabaseFakeImpl
import com.todaysquare.kodeinfeatmvvm.data.databases.QuoteDao
import com.todaysquare.kodeinfeatmvvm.data.repositories.QuoteRepository
import com.todaysquare.kodeinfeatmvvm.data.repositories.QuoteRepositoryImpl
import com.todaysquare.kodeinfeatmvvm.ui.quotes.QuotesViewModelFactory

import org.kodein.di.*

class QuotesApplication : Application(), DIAware {
    override val di = DI.lazy {
        bind<AppDatabase>() with singleton { AppDatabaseFakeImpl() }
        bind<QuoteDao>() with singleton { instance<AppDatabase>().quoteDao }
        bind<QuoteRepository>() with singleton { QuoteRepositoryImpl(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()

        val lazyDI = di

        println(lazyDI)

    }
}