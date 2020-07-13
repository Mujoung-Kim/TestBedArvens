package com.todaysquare.publicthemovieapp.di

import com.todaysquare.publicthemovieapp.di.modules.AppModule
import com.todaysquare.publicthemovieapp.di.modules.MovieModule
import com.todaysquare.publicthemovieapp.di.modules.NetworkModule
import com.todaysquare.publicthemovieapp.ui.movie.MovieFragment

import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MovieModule::class, NetworkModule::class])
interface MovieComponent {

    fun inject(movieFragment: MovieFragment)

}