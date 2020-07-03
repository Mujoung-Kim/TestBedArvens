package com.todaysquare.publicmovieapisample.ui.movies

import com.todaysquare.publicmovieapisample.data.databases.entites.Movie

import io.reactivex.Observable

class MovieManager() {

    fun getMovieList(): Observable<List<Movie>> {
        return Observable.create { subscriber ->
            val movieList = mutableListOf<Movie>()

            for (i in 1..10) {
                movieList.add(
                    Movie(
                        12,
                        5.0f,
                        "we go uerofa",
                        "2020-07-03",
                        "https://picsum.photos/480/640?image=$i",
                        "tottenham is middle"
                    )
                )

            }
            subscriber.onNext(movieList)
            subscriber.onComplete()

        }
    }
}