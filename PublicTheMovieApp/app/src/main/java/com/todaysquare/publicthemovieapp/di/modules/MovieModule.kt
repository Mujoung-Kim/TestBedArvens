package com.todaysquare.publicthemovieapp.di.modules

import com.todaysquare.publicthemovieapp.data.network.TheMovieApi
import com.todaysquare.publicthemovieapp.data.network.TheMovieRestApi
import com.todaysquare.publicthemovieapp.data.network.TheMovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MovieModule {

    @Provides
    @Singleton
    fun provideTheMovieService(retrofit: Retrofit): TheMovieService =
        retrofit.create(TheMovieService::class.java)

    @Provides
    @Singleton
    fun provideTheMovieApi(theMovieService: TheMovieService): TheMovieApi =
        TheMovieRestApi(theMovieService)

}