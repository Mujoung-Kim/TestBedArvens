package com.todaysquare.dagger2_example.service

import com.todaysquare.dagger2_example.data.models.Balance
import com.todaysquare.dagger2_example.utils.Constants.Param.Companion.API_KEY
import com.todaysquare.dagger2_example.utils.Constants.Url.Companion.GET_BALANCE

import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Query

interface BalanceService {

    @GET(GET_BALANCE)
    fun getBalance(
        @Query(API_KEY) api_key: String
    ): Single<Balance>
}