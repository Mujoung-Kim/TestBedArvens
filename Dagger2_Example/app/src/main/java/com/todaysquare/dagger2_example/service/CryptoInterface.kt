package com.todaysquare.dagger2_example.service

import com.todaysquare.dagger2_example.data.cryptomodels.CryptoModel
import com.todaysquare.dagger2_example.utils.Constants.Url.Companion.GET_CRYPTO

import retrofit2.Call
import retrofit2.http.GET

interface CryptoInterface {

    @GET(GET_CRYPTO)
    fun getData(): Call<CryptoModel>

}