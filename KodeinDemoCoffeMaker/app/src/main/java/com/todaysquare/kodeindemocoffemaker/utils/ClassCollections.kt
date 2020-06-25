package com.todaysquare.kodeindemocoffemaker.utils

import com.todaysquare.kodeindemocoffemaker.di.CommonLogger

abstract class Ration(val logger: CommonLogger) { abstract fun name(): String }

class Coffee(logger: CommonLogger) : Ration(logger) {
    init { logger.log("<Creating CoffeeRation>") }

    override fun name(): String = "coffee"

}

class Tea(logger: CommonLogger) : Ration(logger) {
    init { logger.log("<Creating TeaRation>") }

    override fun name(): String = "tea"

}