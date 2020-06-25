package com.todaysquare.kodeindemocoffemaker.di.coffee

import com.todaysquare.kodeindemocoffemaker.di.CommonLogger

class Thermosiphon(private val log: CommonLogger, private val heater: Heater) : Pump {
    init { log.log("<Creating Thermosiphon>") }

    override fun pumpWater() {
        if (heater.isHot) log.log("=> => pumping => =>")
        else log.log("!!! water is cold !!!")

    }
}