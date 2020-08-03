package com.todaysquare.coronatestapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import com.todaysquare.coronatestapplication.R
import com.todaysquare.coronatestapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initNavigation()

    }

    private fun initNavigation() {
        val navController = findNavController(R.id.main_nav_host)

        binding.mainBottomNavigation.setupWithNavController(navController)

    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

    }
}