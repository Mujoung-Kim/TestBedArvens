package com.todaysquare.kodeindemocoffemaker.di.coffee

import com.todaysquare.kodeindemocoffemaker.di.CommonLogger

class ElectricHeater(private val log: CommonLogger) : Heater {
    override val isHot: Boolean get() = heating
    private var heating = false

    init {
        log.log("<Creating ElectricHeater>")

    }

    override fun on() {
        log.log("~ ~ ~ Heating ~ ~ ~")
        this.heating = true

    }

    override fun off() {

    }
}