package com.todaysquare.coronatestapplication.di.modules

import com.todaysquare.coronatestapplication.data.repository.CityRepository
import com.todaysquare.coronatestapplication.data.repository.CountryRepository
import com.todaysquare.coronatestapplication.data.repository.impl.CityRepositoryImpl
import com.todaysquare.coronatestapplication.data.repository.impl.CountryRepositoryImpl

import org.koin.dsl.module

val repositoryModule = module {
    single<CountryRepository> { CountryRepositoryImpl(get(), get(), get()) }
    single<CityRepository> { CityRepositoryImpl(get(), get(), get()) }

}