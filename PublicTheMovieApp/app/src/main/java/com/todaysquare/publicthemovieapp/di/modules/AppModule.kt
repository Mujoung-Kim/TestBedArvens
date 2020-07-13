package com.todaysquare.publicthemovieapp.di.modules

import android.app.Application
import android.content.Context

import com.todaysquare.publicthemovieapp.TheMoviePopularApp

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class AppModule(val app: TheMoviePopularApp) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): Application = app

}