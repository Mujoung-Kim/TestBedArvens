package com.todaysquare.retrofitfunctiontestbed.data.network

import com.todaysquare.retrofitfunctiontestbed.data.model.Library
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Param.Companion.LIB_KEY
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Url.Companion.LIBR_INFO

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SeoulOpenService {

    @GET(LIBR_INFO)
    fun getLibrary(@Path(LIB_KEY) key: String): Call<Library>

}