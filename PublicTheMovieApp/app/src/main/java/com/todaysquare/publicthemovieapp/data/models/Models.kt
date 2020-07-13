package com.todaysquare.publicthemovieapp.data.models

import android.os.Parcelable

import com.todaysquare.publicthemovieapp.ui.adapter.AdapterType
import com.todaysquare.publicthemovieapp.ui.adapter.ViewType

import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(
    var page: Int?,
    val results: List<MovieItem>
) : Parcelable

@Parcelize
data class MovieItem(
    val vote_count: Int,
    val vote_average: Float,
    val title: String,
    val release_date: String,
    val poster_path: String,
    val overview: String?
) : ViewType, Parcelable {
    override fun getViewType() = AdapterType.MOVIE

}