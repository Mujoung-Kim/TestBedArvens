package com.todaysquare.simplefragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment,
                MyFragment().apply {
                    arguments = Bundle().apply { putString("kkk", "this url") }
                }).commit()

    }
}
