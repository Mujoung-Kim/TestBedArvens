package com.todaysquare.publicthemovieapp.data.network

import com.todaysquare.publicthemovieapp.data.network.responses.MovieListResponse
import com.todaysquare.publicthemovieapp.utils.Constants.Url.Companion.GET_DISCOVER
import com.todaysquare.publicthemovieapp.utils.Constants.Url.Companion.IMAGE_URL

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Streaming
import retrofit2.http.Url

interface TheMovieService {

    @GET(GET_DISCOVER)
    fun getTop(@QueryMap par: Map<String, String>): Call<MovieListResponse>

    @GET(GET_DISCOVER)
    fun getDeferredTop(@QueryMap par: Map<String, String>): Deferred<MovieListResponse>

//    @GET("$IMAGE_URL{poster_path}")

//    @Streaming
//    @GET("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg")
//    fun downloadImage(@Url imageUrl: String): Call<ResponseBody>

}