package com.todaysquare.iamportexample.data.network

import com.todaysquare.iamportexample.data.network.responses.TokenResponse
import com.todaysquare.iamportexample.utils.Constants.Param.Companion.IMP_KEY
import com.todaysquare.iamportexample.utils.Constants.Param.Companion.IMP_SECRET
import com.todaysquare.iamportexample.utils.Constants.Url.Companion.BASE_URL
import com.todaysquare.iamportexample.utils.Constants.Url.Companion.POST_GET_TOKEN

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
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

        }
    }

    @FormUrlEncoded
    @POST(POST_GET_TOKEN)
    fun getToken(
        @Field(IMP_KEY) imp_key: String,
        @Field(IMP_SECRET) imp_secret: String
    ): Response<TokenResponse>

}