package com.todaysquare.publicmovieapisample.data.databases.entites

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(
    var page: Int?,
    val results: List<Movie>

) : Parcelable