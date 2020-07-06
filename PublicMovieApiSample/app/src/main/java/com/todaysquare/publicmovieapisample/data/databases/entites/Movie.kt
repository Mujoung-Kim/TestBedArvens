package com.todaysquare.publicmovieapisample.data.databases.entites

import android.os.Parcelable
import com.todaysquare.publicmovieapisample.ui.adapters.AdapterType
import com.todaysquare.publicmovieapisample.ui.adapters.ViewType

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val vote_count: Int,
    val vote_average: Float,
    val title: String,
    val release_data: String,
    val poster_path: String,
    val overview: String?

) : ViewType, Parcelable {

    override fun getViewType(): Int = AdapterType.MOVIE

    /*constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()
    )

    override fun describeContents(): Int = 0

    companion object : Parceler<Movie> {
        override fun Movie.write(parcel: Parcel, flags: Int) = with(parcel) {
            writeInt(vote_count)
            writeFloat(vote_average)
            writeString(title)
            writeString(release_data)
            writeString(poster_path)
            writeString(overview)

        }

        override fun create(parcel: Parcel): Movie = Movie(parcel)

    }*/
}