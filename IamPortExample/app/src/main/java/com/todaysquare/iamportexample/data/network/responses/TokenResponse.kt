package com.todaysquare.iamportexample.data.network.responses

import androidx.annotation.Keep

@Keep
data class TokenResponse(
    var access_token: String,
    var expired_at: Int,
    var now: Int

)