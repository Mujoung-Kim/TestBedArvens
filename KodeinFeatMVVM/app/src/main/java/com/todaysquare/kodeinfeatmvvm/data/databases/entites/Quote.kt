package com.todaysquare.kodeinfeatmvvm.data.databases.entites

data class Quote(val text: String, val author: String) { override fun toString(): String = "$text - $author" }