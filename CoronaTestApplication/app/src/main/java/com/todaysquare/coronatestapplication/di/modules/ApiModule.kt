package com.todaysquare.coronatestapplication.di.modules

import com.todaysquare.coronatestapplication.data.network.ApiInterface
import com.todaysquare.coronatestapplication.utils.Constants.Url.Companion.BASE_URL

import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single<ApiInterface> { get<Retrofit>().create(ApiInterface::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}