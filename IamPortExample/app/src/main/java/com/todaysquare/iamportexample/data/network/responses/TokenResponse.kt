package com.todaysquare.iamportexample.data.network.responses

data class TokenResponse(
    val access_token: String,
    val expired_at: Int,
    val now: Int

)