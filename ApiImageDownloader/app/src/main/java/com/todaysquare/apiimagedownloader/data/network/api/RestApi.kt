package com.todaysquare.apiimagedownloader.data.network.api

import android.widget.Toast
import com.todaysquare.apiimagedownloader.MainActivity
import com.todaysquare.apiimagedownloader.data.network.RiotService
import com.todaysquare.apiimagedownloader.data.network.response.ApiModels
import com.todaysquare.apiimagedownloader.di.adapter.CustomAdapter
import com.todaysquare.apiimagedownloader.utils.Constants.Url.Companion.BASE_URL

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestApi {
    private val riotService: RiotService
    val mainActivity = MainActivity()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        riotService = retrofit.create(RiotService::class.java)

    }

    fun startRetrofit(mAdapter: CustomAdapter) {
        riotService.getData().enqueue(object : Callback<List<ApiModels>> {
            override fun onFailure(call: Call<List<ApiModels>>, t: Throwable) {
//                Toast.makeText(mainActivity, "not connected.", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<ApiModels>>, response: Response<List<ApiModels>>) {
                mAdapter.userList.addAll(response.body() as List<ApiModels>)
                mAdapter.notifyDataSetChanged()

            }
        })

    }
}