package com.todaysquare.kodeindemocoffemaker.di.coffee

import com.todaysquare.kodeindemocoffemaker.di.CommonLogger
import com.todaysquare.kodeindemocoffemaker.utils.Ration

class Kettle<T : Ration> (private val logger: CommonLogger, private val heater: Heater,
    private val pump: Pump, private val ration: () -> T) {
    init { logger.log("<Creating CoffeeMaker>") }

    fun brew() {
        heater.on()
        pump.pumpWater()

        val ration = ration()

        logger.log("[_]P ${ration.name()} [_]P")
        heater.off()

    }
}