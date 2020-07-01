package com.todaysquare.daggermultibinding.data.network

import com.todaysquare.daggermultibinding.data.models.DataModel
import com.todaysquare.daggermultibinding.utils.Constants.Url.Companion.API_USER

import io.reactivex.Single

import retrofit2.http.GET

interface ApiClient {

    @GET(API_USER)
    fun getData(): Single<DataModel>

}