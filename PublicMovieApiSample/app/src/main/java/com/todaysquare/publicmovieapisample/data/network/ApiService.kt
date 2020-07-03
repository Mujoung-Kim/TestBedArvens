package com.todaysquare.publicmovieapisample.data.network

import com.todaysquare.publicmovieapisample.data.network.responses.MovieListResponse
import com.todaysquare.publicmovieapisample.utils.Constants.Url.Companion.GET_DISCOVER

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    companion object {

    }

    @GET(GET_DISCOVER)
    fun getTop(@QueryMap par: Map<String, String>): Call<MovieListResponse>

}