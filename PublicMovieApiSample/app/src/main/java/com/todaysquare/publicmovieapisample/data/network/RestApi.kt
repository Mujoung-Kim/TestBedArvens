package com.todaysquare.publicmovieapisample.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.todaysquare.publicmovieapisample.data.network.responses.MovieListResponse
import com.todaysquare.publicmovieapisample.utils.Constants.Url.Companion.BASE_URL

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestApi {
    private val theMovieService: TheMovieService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        theMovieService = retrofit.create(TheMovieService::class.java)

    }

    fun getMovieListRetrofit(param: Map<String, String>): Call<MovieListResponse> =
        theMovieService.getTop(param)

    suspend fun getMovieListCo(param: Map<String, String>): MovieListResponse =
        theMovieService.getDeferredTop(param).await()

}