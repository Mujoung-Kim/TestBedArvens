package com.todaysquare.kodeindemocoffemaker

import android.app.Activity
import android.app.Application

import com.todaysquare.kodeindemocoffemaker.di.coffee.Kettle
import com.todaysquare.kodeindemocoffemaker.utils.Coffee
import com.todaysquare.kodeindemocoffemaker.utils.electricHeaterModule

import org.kodein.di.*
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bindings.WeakContextScope

class DemoApplication : Application(), DIAware {
    override val di by DI.lazy {
        import(androidXModule(this@DemoApplication))

        bind() from instance(AndroidLogger())

        import(electricHeaterModule)

        bind<Coffee>() with provider { Coffee(instance()) }
        bind<Kettle<Coffee>>() with scoped(WeakContextScope.of<Activity>())
            .singleton { Kettle<Coffee>(instance(), instance(), instance(), provider()) }

    }

    override fun onCreate() {
        super.onCreate()

        val lazyDI = di

        println(lazyDI)

    }
}