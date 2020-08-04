package com.todaysquare.coronatestapplication.di.modules

import com.todaysquare.coronatestapplication.utils.NetworkManager

import org.koin.dsl.module

val networkModule = module {
    single { NetworkManager(get()) }

}