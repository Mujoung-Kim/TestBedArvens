package com.todaysquare.daggermultibinding.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.todaysquare.daggermultibinding.R
import com.todaysquare.daggermultibinding.ui.ViewModelFactory

import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainActivityViewModel
                = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

        mainActivityViewModel.dataResponse.observe(this, Observer {
            progress_circular.visibility = View.GONE

        })

    }
}
