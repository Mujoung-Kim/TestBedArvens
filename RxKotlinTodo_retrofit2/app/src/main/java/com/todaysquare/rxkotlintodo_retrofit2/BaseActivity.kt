package com.todaysquare.rxkotlintodo_retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity : AppCompatActivity() {

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateBaseActivity(savedInstanceState)

    }

    abstract fun onCreateBaseActivity(savedInstanceState: Bundle?)

}
