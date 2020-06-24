package com.todaysquare.iamportsamplecode.data.network.responses

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.BIRTH
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.CERTIFIED
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.CER_AT
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.GENDER
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.NAME
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.PHONE

@Keep
data class ProfileResponse(
    @SerializedName(BIRTH) var birth: Int,
    @SerializedName(CERTIFIED) var certified: Boolean,
    @SerializedName(CER_AT) var certified_at: Int,
    @SerializedName(GENDER) var gender: String?,
    @SerializedName(NAME) var name: String?,
    @SerializedName(PHONE) var phone: String?

)