package com.todaysquare.publicmovieapisample.data.network

import com.todaysquare.publicmovieapisample.data.network.responses.MovieListResponse
import com.todaysquare.publicmovieapisample.utils.Constants.Url.Companion.BASE_URL
import com.todaysquare.publicmovieapisample.utils.Constants.Url.Companion.GET_DISCOVER
import kotlinx.coroutines.Deferred

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

/*
interface ApiService {
    companion object {
        operator fun invoke(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(ApiService::class.java)

        }
    }

    fun getMovieList(param: Map<String, String>): Call<MovieListResponse> = invoke().getTop(param)

    suspend fun getMovieListCo(param: Map<String, String>): MovieListResponse =
        invoke().getDeferredTop(param).await()

    @GET(GET_DISCOVER)
    fun getTop(@QueryMap par: Map<String, String>): Call<MovieListResponse>

    @GET(GET_DISCOVER)
    fun getDeferredTop(@QueryMap par: Map<String, String>): Deferred<MovieListResponse>

}*/
