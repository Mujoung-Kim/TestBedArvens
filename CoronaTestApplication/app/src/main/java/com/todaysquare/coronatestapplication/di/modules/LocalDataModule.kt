package com.todaysquare.coronatestapplication.di.modules

import androidx.room.Room

import com.todaysquare.coronatestapplication.data.database.CoronaDatabase
import com.todaysquare.coronatestapplication.data.repository.local.CityLocalDataSource
import com.todaysquare.coronatestapplication.data.repository.local.CityLocalDataSourceImpl
import com.todaysquare.coronatestapplication.data.repository.local.CountryLocalDataSource
import com.todaysquare.coronatestapplication.data.repository.local.CountryLocalDataSourceImpl

import org.koin.dsl.module

val localDataModule = module {
    single<CountryLocalDataSource> { CountryLocalDataSourceImpl(get()) }
    single<CityLocalDataSource> { CityLocalDataSourceImpl(get()) }
    single { get<CoronaDatabase>().countryDao() }
    single { get<CoronaDatabase>().cityDao() }

    single {
        Room.databaseBuilder(get(), CoronaDatabase::class.java, "Corona.db")
            .allowMainThreadQueries()
            .build()

    }
}