package com.todaysquare.coronatestapplication.di.modules

import com.todaysquare.coronatestapplication.data.repository.local.CountryLocalDataSource
import com.todaysquare.coronatestapplication.data.repository.local.CountryLocalDataSourceImpl
import com.todaysquare.coronatestapplication.data.repository.remote.CityRemoteDataSource
import com.todaysquare.coronatestapplication.data.repository.remote.CityRemoteDataSourceImpl

import org.koin.dsl.module

val remoteModule = module {
    single<CountryLocalDataSource> { CountryLocalDataSourceImpl(get()) }
    single<CityRemoteDataSource> { CityRemoteDataSourceImpl(get()) }

}