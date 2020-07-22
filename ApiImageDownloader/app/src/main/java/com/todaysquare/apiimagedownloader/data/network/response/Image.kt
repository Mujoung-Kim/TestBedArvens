package com.todaysquare.apiimagedownloader.data.network.response

data class Image(
    val full: String,
    val group: String,
    val h: Int,
    val sprite: String,
    val w: Int,
    val x: Int,
    val y: Int
)