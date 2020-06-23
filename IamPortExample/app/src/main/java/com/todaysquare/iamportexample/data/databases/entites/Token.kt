package com.todaysquare.iamportexample.data.databases.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

import com.todaysquare.iamportexample.data.network.responses.TokenResponse

@Entity(tableName = "Tokens")
data class Token(
    @PrimaryKey
    val code: Int,
    val message: String,
    var tokenResponse: TokenResponse

)