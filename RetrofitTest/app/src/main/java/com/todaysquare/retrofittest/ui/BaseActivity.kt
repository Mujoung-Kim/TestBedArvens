package com.todaysquare.retrofittest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.todaysquare.retrofittest.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

    }
}
