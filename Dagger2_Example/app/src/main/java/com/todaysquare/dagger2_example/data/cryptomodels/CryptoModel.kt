package com.todaysquare.dagger2_example.data.cryptomodels

data class CryptoModel(
    val Data: Data,
    val HasWarning: Boolean,
    val Message: String,
    val RateLimit: RateLimit,
    val Response: String,
    val Type: Int
)