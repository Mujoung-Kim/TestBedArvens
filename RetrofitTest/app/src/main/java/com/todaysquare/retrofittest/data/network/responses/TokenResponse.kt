package com.todaysquare.retrofittest.data.network.responses

data class TokenResponse(
    val access_token: String,
    val expired_at: Int,
    val now: Int

)