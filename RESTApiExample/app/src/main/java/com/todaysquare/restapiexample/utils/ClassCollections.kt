package com.todaysquare.restapiexample.utils

data class Data(val data: Accepted)
data class Accepted(val accepted: HashMap<String, Friend>)
data class Friend(val success: Boolean, val message: String)