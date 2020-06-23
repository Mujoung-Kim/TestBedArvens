package com.todaysquare.iamportexample.data.databases

import com.todaysquare.iamportexample.data.network.responses.TokenResponse

data class Token(
    val code: Int,
    val message: String,
    val tokenResponse: TokenResponse

)