package com.todaysquare.retrofitfunctiontestbed.data.network.api

import com.todaysquare.retrofitfunctiontestbed.data.network.GithubService
import com.todaysquare.retrofitfunctiontestbed.data.repository.RepositoryItem
import com.todaysquare.retrofitfunctiontestbed.ui.adapter.CustomAdapter
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Url.Companion.BASE_GIT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitRestApi {
    private val githubService: GithubService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_GIT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        githubService = retrofit.create(GithubService::class.java)

    }

    fun startRetrofit(mAdapter: CustomAdapter) {
        githubService.users().enqueue(object : Callback<List<RepositoryItem>> {
            override fun onFailure(call: Call<List<RepositoryItem>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<RepositoryItem>>,
                response: Response<List<RepositoryItem>>) {
                mAdapter.userList.addAll(response.body() as List<RepositoryItem>)
                mAdapter.notifyDataSetChanged()

            }
        })

    }
}