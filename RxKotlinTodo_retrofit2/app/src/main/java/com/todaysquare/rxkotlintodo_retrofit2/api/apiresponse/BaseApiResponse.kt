package com.todaysquare.rxkotlintodo_retrofit2.api.apiresponse

import com.google.gson.annotations.SerializedName

import java.io.Serializable

open class BaseApiResponse(
    @SerializedName("error_code")
    val error_code: Int,
    @SerializedName("error_message")
    val error_message: String

) : Serializable