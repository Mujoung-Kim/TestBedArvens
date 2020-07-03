package com.todaysquare.publicmovieapisample.data.network.responses

import android.os.Parcelable

import com.todaysquare.publicmovieapisample.data.databases.entites.Movie

import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListResponse(
    var page: Int,
    val results: List<Movie>

) : Parcelable