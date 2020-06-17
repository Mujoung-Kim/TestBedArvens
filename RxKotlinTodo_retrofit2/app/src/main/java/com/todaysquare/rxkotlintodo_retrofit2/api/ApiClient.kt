package com.todaysquare.rxkotlintodo_retrofit2.api

import com.todaysquare.rxkotlintodo_retrofit2.utils.Constants.BASE_URL

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

import java.util.concurrent.TimeUnit

class ApiClient {
    private var retrofit: Retrofit? = null

    enum class LogLevel {
        LOG_NOT_NEEDED,
        LOG_REQ_RES,
        LOG_REQ_RES_BODY_HEADERS,
        LOG_REQ_RES_HEADERS_ONLY

    }

    fun getClient(logLevel: LogLevel): Retrofit {
        val interceptor = HttpLoggingInterceptor()

        when (logLevel) {
            LogLevel.LOG_NOT_NEEDED -> interceptor.level = HttpLoggingInterceptor.Level.NONE
            LogLevel.LOG_REQ_RES -> interceptor.level = HttpLoggingInterceptor.Level.BASIC
            LogLevel.LOG_REQ_RES_BODY_HEADERS -> interceptor.level =
                HttpLoggingInterceptor.Level.BODY
            LogLevel.LOG_REQ_RES_HEADERS_ONLY -> interceptor.level =
                HttpLoggingInterceptor.Level.HEADERS

        }

        val client = OkHttpClient.Builder().connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES).readTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(interceptor).build()

        if (null == retrofit) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        }
        return retrofit!!

    }

    fun getApiService(logLevel: LogLevel = LogLevel.LOG_REQ_RES_BODY_HEADERS) =
        getClient(logLevel).create(ApiService::class.java)

}