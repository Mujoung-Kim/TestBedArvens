package com.todaysquare.iamportsamplecode.data.databases.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.todaysquare.iamportsamplecode.data.network.responses.ProfileResponse
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.CODE
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.MESSAGE
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.RESPONSE

@Keep
class Profile(
    @SerializedName(CODE) var code: Int,
    @SerializedName(MESSAGE) var message: String?,
    @SerializedName(RESPONSE) var response: ProfileResponse

)