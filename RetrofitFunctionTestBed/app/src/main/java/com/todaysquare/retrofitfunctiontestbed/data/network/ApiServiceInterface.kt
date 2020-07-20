package com.todaysquare.retrofitfunctiontestbed.data.network

import com.todaysquare.retrofitfunctiontestbed.data.model.Funding
import com.todaysquare.retrofitfunctiontestbed.data.network.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.*

//  Retrofit service interface
interface ApiServiceInterface {

    //  request & response api server communication interface.
//    @GET("users/current/durations")
//    fun getCodingTime(
//        @Query("date") date: String,
//        @Query("api_key") api_key: String
//    ): Call<RawResponseData>
//
//    @POST("signup")
//    @FormUrlEncoded
//    fun singup(
//        @Field("email") email: String,
//        @Field("plain_password") password: String,
//        @Field("name") name: String
//    ): Call<DataModel.SignUpResponse>
//
//    @PUT("user/basic")
//    fun uploadBasicInfo(
//        @Query("email") email: String,
//        @Query("age") age: Int,
//        @Query("gender") gender: Int,
//        @Query("one_line") one_line: String?
//    ): Call<DataModel.PutResponse>

//    @GET("mypage/fundlist")
//    fun listFundingHistories(): Call<ResponseWrapper<List<Funding>>>

//    @GET("mypage/fundlist")
//    fun listFundingHistories(): Call<List<Funding>>

}

// const val BASE_URL = "https://random.api.server.com/api/"
//