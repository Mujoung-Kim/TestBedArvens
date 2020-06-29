package com.todaysquare.dagger2_example.di.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

import com.todaysquare.dagger2_example.BaseApplication

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class SharedModule(private val application: BaseApplication) {
    @Provides
    fun context(): Context = application

    @Provides
    @Singleton
    fun sharedPerf(context: Context): SharedPreferences =
        context.getSharedPreferences("API", MODE_PRIVATE)

}