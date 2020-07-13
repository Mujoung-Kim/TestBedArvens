package com.todaysquare.publicthemovieapp

import android.app.Application

import com.todaysquare.publicthemovieapp.di.DaggerMovieComponent
import com.todaysquare.publicthemovieapp.di.MovieComponent

class TheMoviePopularApp : Application() {
    companion object {
        lateinit var movieComponent: MovieComponent

    }

    override fun onCreate() {
        super.onCreate()

        movieComponent = DaggerMovieComponent.builder().build()

    }
}