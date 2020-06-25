package com.todaysquare.kodeindemocoffemaker.utils

import com.todaysquare.kodeindemocoffemaker.di.coffee.ElectricHeater
import com.todaysquare.kodeindemocoffemaker.di.coffee.Heater
import com.todaysquare.kodeindemocoffemaker.di.coffee.Pump
import com.todaysquare.kodeindemocoffemaker.di.coffee.Thermosiphon

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val electricHeaterModule = DI.Module("Electric Heater") {
    bind<Heater>() with singleton { ElectricHeater(instance()) }

}

val thermosiphonModule = DI.Module("Thermosiphon") {
    bind<Pump>() with singleton { Thermosiphon(instance(), instance()) }

}