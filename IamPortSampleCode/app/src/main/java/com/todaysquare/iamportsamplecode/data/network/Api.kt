package com.todaysquare.iamportsamplecode.data.network

import com.google.gson.GsonBuilder
import com.todaysquare.iamportsamplecode.data.network.responses.TokenResponse
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.IMP_KEY
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.IMP_SECRET
import com.todaysquare.iamportsamplecode.utils.Constants.Url.Companion.BASE_URL
import com.todaysquare.iamportsamplecode.utils.Constants.Url.Companion.POST_GET_TOKEN

import okhttp3.OkHttpClient

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

import java.util.concurrent.TimeUnit

interface Api {
    companion object {
        fun createForImport(networkConnectionInterceptor: NetworkConnectionInterceptor): Api {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .addInterceptor(networkConnectionInterceptor)
                .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(Api::class.java)

        }
    }

    @FormUrlEncoded
    @POST(POST_GET_TOKEN)
    suspend fun postAccessToken(
        @Field(IMP_KEY) imp_key: String,
        @Field(IMP_SECRET) imp_secret: String

    ): Response<TokenResponse>

}