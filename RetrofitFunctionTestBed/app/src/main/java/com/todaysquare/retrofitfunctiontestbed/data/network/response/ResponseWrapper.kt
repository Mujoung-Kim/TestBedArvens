package com.todaysquare.retrofitfunctiontestbed.data.network.response

import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ResponseWrapper<T>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: @RawValue T? = null

) : Parcelable