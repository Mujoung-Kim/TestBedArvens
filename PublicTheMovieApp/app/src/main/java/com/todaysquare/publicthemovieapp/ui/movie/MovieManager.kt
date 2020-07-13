package com.todaysquare.publicthemovieapp.ui.movie

import com.todaysquare.publicthemovieapp.data.models.MovieItem
import com.todaysquare.publicthemovieapp.data.models.MovieList
import com.todaysquare.publicthemovieapp.data.network.responses.MovieListResponse
import com.todaysquare.publicthemovieapp.data.network.TheMovieApi

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieManager @Inject constructor(private val api: TheMovieApi) {

    suspend fun getMovieList(param: Map<String, String>): MovieList {
        val result = api.getMovieListCo(param)

        //  result 값 확인
        return process(result)

    }

    private fun process(response: MovieListResponse): MovieList {
        val list = response.results.map {
            MovieItem(
                it.vote_count,
                it.vote_average,
                it.title,
                it.release_date,
                it.poster_path,
                it.overview
            )
        }

        //  response.page 값 및 results 에 값 들어갔는지 확인
        return MovieList(
            response.page,
            list
        )

    }
}