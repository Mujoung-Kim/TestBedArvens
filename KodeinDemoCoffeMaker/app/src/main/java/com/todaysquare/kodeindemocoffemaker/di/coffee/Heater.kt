package com.todaysquare.kodeindemocoffemaker.di.coffee

interface Heater {
    val isHot: Boolean

    fun on()
    fun off()

}