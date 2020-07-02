package com.todaysquare.koinmovieexample.data.network

import com.todaysquare.koinmovieexample.data.MovieCollection
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.API_KEY
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.PAGE
import com.todaysquare.koinmovieexample.utils.Constants.Url.Companion.POPULAR

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(POPULAR)
    suspend fun popularMovies(
        @Query(API_KEY, encoded = false) apiKey: String,
        @Query(PAGE) pageNumber: Int = 1
    ): MovieCollection

}