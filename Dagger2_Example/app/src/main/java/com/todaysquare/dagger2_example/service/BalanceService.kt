package com.todaysquare.dagger2_example.service

import com.todaysquare.dagger2_example.data.models.Balance
import com.todaysquare.dagger2_example.utils.Constants.Param.Companion.AMOUNTS
import com.todaysquare.dagger2_example.utils.Constants.Param.Companion.API_KEY
import com.todaysquare.dagger2_example.utils.Constants.Param.Companion.TO_ADDRESSES
import com.todaysquare.dagger2_example.utils.Constants.Param.Companion.TYPE
import com.todaysquare.dagger2_example.utils.Constants.Url.Companion.GET_BALANCE
import com.todaysquare.dagger2_example.utils.Constants.Url.Companion.GET_TRANSACTIONS
import com.todaysquare.dagger2_example.utils.Constants.Url.Companion.POST_WITHDRAW

import io.reactivex.Single

import retrofit2.http.*

interface BalanceService {

    @GET(GET_BALANCE)
    fun getBalance(
        @Query(API_KEY) api_key: String
    ): Single<Balance>

    @POST(POST_WITHDRAW)
    @FormUrlEncoded
    fun withDrawCoin(
        @Field(API_KEY) api_key: String,
        @Field(AMOUNTS) amount: String,
        @Field(TO_ADDRESSES) to_addresses: String
    ): Single<Balance>

    @GET(GET_TRANSACTIONS)
    fun getTransaction(
        @Query(API_KEY) api_key: String,
        @Query(TYPE) type: String
    ): Single<Balance>

}