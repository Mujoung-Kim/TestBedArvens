package com.todaysquare.apiimagedownloader.data.network.response

data class Ezreal(
    val allytips: List<String>,
    val blurb: String,
    val enemytips: List<String>,
    val id: String,
    val image: Image,
    val key: String,
    val lore: String,
    val name: String,
    val partype: String,
    val skins: List<Skin>,
    val stats: Stats,
    val tags: List<String>,
    val title: String
)