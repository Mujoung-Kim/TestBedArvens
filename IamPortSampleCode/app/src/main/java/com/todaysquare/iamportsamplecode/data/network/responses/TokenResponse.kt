package com.todaysquare.iamportsamplecode.data.network.responses

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.ACC_TOKEN
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.EXPIRED_AT
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.NOW

@Keep
data class TokenResponse(
    @SerializedName(ACC_TOKEN) var access_token: String,
    @SerializedName(NOW) var now: Int,
    @SerializedName(EXPIRED_AT) var expired_at: Int

)