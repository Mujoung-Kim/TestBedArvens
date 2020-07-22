package com.todaysquare.apiimagedownloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.todaysquare.apiimagedownloader.data.network.api.RestApi
import com.todaysquare.apiimagedownloader.data.network.response.ApiModels
import com.todaysquare.apiimagedownloader.di.adapter.CustomAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "${this.javaClass}"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAdapter = CustomAdapter()

        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonMain.setOnClickListener {
            val restApi = RestApi()

            restApi.startRetrofit(mAdapter)

        }
    }
}