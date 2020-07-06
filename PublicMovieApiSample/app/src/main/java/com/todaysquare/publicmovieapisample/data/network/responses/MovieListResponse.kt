package com.todaysquare.publicmovieapisample.data.network.responses

data class MovieListResponse(
    var page: Int,
    val results: List<MovieDetailResponse>

)