package com.todaysquare.publicmovieapisample.ui.movies

import com.todaysquare.publicmovieapisample.data.databases.entites.Movie
import com.todaysquare.publicmovieapisample.data.databases.entites.MovieList
import com.todaysquare.publicmovieapisample.data.network.TheMovieApi
import com.todaysquare.publicmovieapisample.data.network.responses.MovieListResponse

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieManager @Inject constructor(private val api: TheMovieApi) {

    /*fun getMovieList(page: String): Observable<List<Movie>> {
        return Observable.create { subscriber ->
            val param = mapOf(
                PAGE to page,
                API_KEY to KEY_VALUE,
                SORT_BY to SORT_POPULARITY,
                LANGUAGE to "ko"
            )
            val call = api.getMovieList(param)
            val response = call.execute()

            if (response.isSuccessful) {
                val movieListResults = response.body()?.results?.map {
                    Movie(
                        it.vote_count,
                        it.vote_average,
                        it.title,
                        it.release_data,
                        it.poster_path,
                        it.overview
                    )
                }
                if (movieListResults != null) {
                    val responsePage = response.body()?.page?.plus(1)
                    val movieList = MovieListResponse(responsePage, movieListResults)

                    subscriber.onNext(movieList)

                }
                subscriber.onComplete()

            } else subscriber.onError(Throwable(response.message()))

        }
    }*/

    suspend fun getMovieList(param: Map<String, String>): MovieList {
        val result = api.getMovieListCo(param)

        return process(result)

    }

    private fun process(response: MovieListResponse): MovieList {
        val list = response.results.map {
            Movie(
                it.vote_count,
                it.vote_average,
                it.title,
                it.release_data,
                it.poster_path,
                it.overview)

        }

        return MovieList(response.page, list)

    }
}