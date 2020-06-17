package com.todaysquare.retrofittest.data.database.entity

import androidx.room.Entity

import com.todaysquare.retrofittest.data.network.responses.TokenResponse

@Entity
data class Token(
    val code: Int,
    val message: String,
    val response: TokenResponse

)