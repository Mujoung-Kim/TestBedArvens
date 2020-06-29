package com.todaysquare.dagger2_example.di.modules

import com.todaysquare.dagger2_example.service.BalanceService
import com.todaysquare.dagger2_example.utils.Constants.Url.Companion.BASE_URL

import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Singleton
    @Provides
    internal fun provideBalanceService(retrofit: Retrofit): BalanceService =
        retrofit.create(BalanceService::class.java)

}