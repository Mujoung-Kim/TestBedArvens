package com.todaysquare.iamportexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.todaysquare.iamportexample.R
import com.todaysquare.iamportexample.data.network.ApiService
import retrofit2.Retrofit

class BaseActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var api: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

    }
}
