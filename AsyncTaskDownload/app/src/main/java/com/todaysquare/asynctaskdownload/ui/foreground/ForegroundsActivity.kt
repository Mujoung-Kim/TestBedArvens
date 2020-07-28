package com.todaysquare.asynctaskdownload.ui.foreground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

import com.todaysquare.asynctaskdownload.R
import com.todaysquare.asynctaskdownload.data.Foreground

import kotlinx.android.synthetic.main.activity_foregrounds.*

class ForegroundsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foregrounds)

        buttonForegroundStart.setOnClickListener {
            val intent = Intent(this, Foreground::class.java)

            ContextCompat.startForegroundService(this, intent)

        }

        buttonForegroundStop.setOnClickListener {
            val intent = Intent(this, Foreground::class.java)

            stopService(intent)

        }
    }
}