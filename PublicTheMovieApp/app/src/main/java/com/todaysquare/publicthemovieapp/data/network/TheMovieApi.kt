package com.todaysquare.publicthemovieapp.data.network

import android.database.Observable
import com.todaysquare.publicthemovieapp.data.network.responses.MovieListResponse

import retrofit2.Call

interface TheMovieApi {

    fun getMovieListRx(param: Map<String, String>): Observable<MovieListResponse>

    fun getMovieListNormal(param: Map<String, String>): Call<MovieListResponse>

    suspend fun getMovieListCo(param: Map<String, String>): MovieListResponse

}