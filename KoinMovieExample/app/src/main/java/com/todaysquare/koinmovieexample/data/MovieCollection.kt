package com.todaysquare.koinmovieexample.data

import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.ID
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.ORIGINAL_TITLE
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.OVERVIEW
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.PAGE
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.POSTER_PATH
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.RELEASE_DATE
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.RESULT
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.VOTE_AVERAGE
import com.todaysquare.koinmovieexample.utils.Event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCollection(
    @SerialName(value = PAGE) val page: Int,
    @SerialName(value = RESULT) val movies: ArrayList<Movie>
) {

    @Serializable
    data class Movie(
        @SerialName(value = ID) val id: Long,
        @SerialName(value = ORIGINAL_TITLE) val name: String,
        @SerialName(value = POSTER_PATH) val posterUrl: String,
        @SerialName(value = VOTE_AVERAGE) val rating: Float,
        @SerialName(value = OVERVIEW) val description: String,
        @SerialName(value = RELEASE_DATE) val releaseDate: String
    ) {
        var isFavorite: Boolean = false

    }
}

data class MovieDataState(
    val showProgress: Boolean,
    val movies: Event<List<MovieCollection.Movie>>?,
    val error: Event<Int>?
)