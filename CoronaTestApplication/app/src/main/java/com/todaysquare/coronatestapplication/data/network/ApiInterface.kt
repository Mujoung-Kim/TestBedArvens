package com.todaysquare.coronatestapplication.data.network

import com.todaysquare.coronatestapplication.data.network.response.CityResponse
import com.todaysquare.coronatestapplication.data.network.response.CountryResponse
import com.todaysquare.coronatestapplication.utils.Constants.Param.Companion.KEY_VAL
import com.todaysquare.coronatestapplication.utils.Constants.Param.Companion.SERVICE_KEY
import com.todaysquare.coronatestapplication.utils.Constants.Url.Companion.GET_CON
import com.todaysquare.coronatestapplication.utils.Constants.Url.Companion.GET_NEW

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(GET_NEW)
    fun getCityInfo(@Query(SERVICE_KEY) serviceKey: String = KEY_VAL): Call<CityResponse>

    @GET(GET_CON)
    fun getCountryInfo(@Query(SERVICE_KEY) serviceKey: String = KEY_VAL): Call<CountryResponse>

}