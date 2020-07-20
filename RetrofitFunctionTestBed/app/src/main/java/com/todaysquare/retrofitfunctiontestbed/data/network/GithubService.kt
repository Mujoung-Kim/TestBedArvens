package com.todaysquare.retrofitfunctiontestbed.data.network

import com.todaysquare.retrofitfunctiontestbed.data.repository.RepositoryItem
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Url.Companion.USER_REPO

import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET(USER_REPO)
    fun users(): Call<List<RepositoryItem>>

}