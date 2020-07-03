package com.todaysquare.publicmovieapisample.data.network.responses

import com.todaysquare.publicmovieapisample.data.databases.entites.Movie

data class MovieListResponse(
    var page: Int,
    val results: List<Movie>

)