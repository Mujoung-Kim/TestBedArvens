package com.todaysquare.patterntasknote.data.databases.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteNotice(
    val num: String,
    val title: String,
    @PrimaryKey
    val link: String

) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!,
        parcel.readString()!!, parcel.readString()!!)

    companion object CREATOR : Parcelable.Creator<FavoriteNotice> {
        override fun createFromParcel(parcel: Parcel): FavoriteNotice = FavoriteNotice(parcel)

        override fun newArray(size: Int): Array<FavoriteNotice?> = arrayOfNulls(size)

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(num)
        parcel.writeString(title)
        parcel.writeString(link)

    }

    override fun describeContents(): Int = 0

}