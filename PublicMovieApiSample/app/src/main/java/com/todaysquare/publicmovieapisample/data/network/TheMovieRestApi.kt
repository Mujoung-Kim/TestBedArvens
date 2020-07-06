package com.todaysquare.publicmovieapisample.data.network

import android.database.Observable

import com.todaysquare.publicmovieapisample.data.network.responses.MovieListResponse

import retrofit2.Call

import javax.inject.Inject

class TheMovieRestApi @Inject constructor(private val theMovieService: TheMovieService)
    : TheMovieApi {

    override fun getMovieListRx(param: Map<String, String>): Observable<MovieListResponse> {
        TODO("Not implemented.")

    }

    override fun getMovieListNormal(param: Map<String, String>): Call<MovieListResponse> =
        theMovieService.getTop(param)

    override suspend fun getMovieListCo(param: Map<String, String>): MovieListResponse =
        theMovieService.getDeferredTop(param).await()

}