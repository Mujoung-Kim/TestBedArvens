package com.todaysquare.kodeindemocoffemaker.ui.main

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.todaysquare.kodeindemocoffemaker.AndroidLogger
import com.todaysquare.kodeindemocoffemaker.R
import com.todaysquare.kodeindemocoffemaker.di.coffee.Kettle
import com.todaysquare.kodeindemocoffemaker.utils.Coffee

import kotlinx.android.synthetic.main.fragment_main.*

import org.kodein.di.DI
import org.kodein.di.android.x.di
import org.kodein.di.DIAware
import org.kodein.di.instance

class MainFragment : Fragment(), DIAware {
    override val di: DI by di()

    private val coffeeMaker by instance<Kettle<Coffee>>()
    private val log by instance<AndroidLogger>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        log.log("onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onStart() {
        super.onStart()

        if (coffeeMaker != (requireActivity() as MainActivity).coffeeMaker) throw AssertionError()

        log.callback = { text.text = log.text }
        log.log("Starting to brew coffee using $coffeeMaker")

        Handler().postDelayed({ coffeeMaker.brew() }, 3000)
        Handler().postDelayed({ coffeeMaker.brew() }, 6000)

    }

    override fun onStop() {
        log.callback = null
        super.onStop()

    }
}
