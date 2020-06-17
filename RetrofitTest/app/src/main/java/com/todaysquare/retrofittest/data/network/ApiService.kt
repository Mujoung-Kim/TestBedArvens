package com.todaysquare.retrofittest.data.network

import com.todaysquare.retrofittest.data.network.ToConstant.Param.Companion.CODE
import com.todaysquare.retrofittest.data.network.ToConstant.Param.Companion.MESSAGE
import com.todaysquare.retrofittest.data.network.ToConstant.Param.Companion.RESPONSE
import com.todaysquare.retrofittest.data.network.ToConstant.Url.Companion.BASE_URL
import com.todaysquare.retrofittest.data.network.ToConstant.Url.Companion.POST_GET_TOKEN
import com.todaysquare.retrofittest.data.network.responses.TokenResponse

import okhttp3.OkHttpClient

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor).build()

            return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)

        }
    }

    @FormUrlEncoded
    @POST(POST_GET_TOKEN)
    fun getToken(
        @Field(CODE) code: Int,
        @Field(MESSAGE) message: String,
        @Field(RESPONSE) response: TokenResponse
    ): Response<TokenResponse>
}