package com.todaysquare.dagger2_example.data.cryptomodels

data class Data(
    val Aggregated: Boolean,
    val Data: List<DataX>,
    val TimeFrom: Int,
    val TimeTo: Int
)