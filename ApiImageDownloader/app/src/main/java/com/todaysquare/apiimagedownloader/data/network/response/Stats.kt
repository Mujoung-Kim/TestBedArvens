package com.todaysquare.apiimagedownloader.data.network.response

data class Stats(
    val armor: Int,
    val armorperlevel: Double,
    val attackdamage: Int,
    val attackdamageperlevel: Double,
    val attackrange: Int,
    val attackspeed: Double,
    val attackspeedperlevel: Int,
    val crit: Int,
    val critperlevel: Int,
    val hp: Int,
    val hpperlevel: Int,
    val hpregen: Double,
    val hpregenperlevel: Double,
    val movespeed: Int,
    val mp: Double,
    val mpperlevel: Int,
    val mpregen: Double,
    val mpregenperlevel: Double,
    val spellblock: Int,
    val spellblockperlevel: Double
)