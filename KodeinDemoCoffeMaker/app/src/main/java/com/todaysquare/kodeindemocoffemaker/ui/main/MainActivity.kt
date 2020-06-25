package com.todaysquare.kodeindemocoffemaker.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.todaysquare.kodeindemocoffemaker.AndroidLogger
import com.todaysquare.kodeindemocoffemaker.R
import com.todaysquare.kodeindemocoffemaker.di.coffee.Kettle
import com.todaysquare.kodeindemocoffemaker.utils.Coffee
import com.todaysquare.kodeindemocoffemaker.utils.thermosiphonModule

import org.kodein.di.*
import org.kodein.di.android.di
import org.kodein.di.android.retainedSubDI

class MainActivity : AppCompatActivity(), DIAware {
    override val diContext: DIContext<*> = diContext(this)
    override val di by retainedSubDI(di(), copy = Copy.All) { import(thermosiphonModule) }

    private val TAG = this.javaClass.simpleName
    private val log by instance<AndroidLogger>()
    val coffeeMaker by instance<Kettle<Coffee>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            log.log("Going to brew coffee using $coffeeMaker")

            supportFragmentManager.beginTransaction().add(R.id.fragment, MainFragment()).commit()

        }

        Log.i(TAG, "============-BINDINGS-============")
        Log.i(TAG, di.container.tree.bindings.description())
        Log.i(TAG, "==================================")

    }
}
