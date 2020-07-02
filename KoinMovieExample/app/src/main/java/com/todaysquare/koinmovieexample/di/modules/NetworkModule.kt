package com.todaysquare.koinmovieexample.di.modules

import android.content.Context

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.todaysquare.koinmovieexample.data.network.ApiService
import com.todaysquare.koinmovieexample.utils.Constants.Url.Companion.BASE_URL

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

import okhttp3.Cache
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import retrofit2.Retrofit

import java.io.File

private const val CACHE_FILE_SIZE: Long = 30 * 1000 * 1000

val retrofitModule = module {
    single<Call.Factory> {
        val cacheFile =
            cacheFile(androidContext())
        val cache = cache(cacheFile)

        okhttp(cache)

    }

    single { retrofit(get(), BASE_URL) }

    single { get<Retrofit>().create(ApiService::class.java) }

}

private val interceptor: Interceptor
    get() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

    }

private fun cacheFile(context: Context) = File(context.filesDir, "my_own_created_cache")
    .apply { if (!this.exists()) mkdir() }

private fun cache(cacheFile: File) = Cache(cacheFile, CACHE_FILE_SIZE)

@OptIn(UnstableDefault::class)
private fun retrofit(callFactory: Call.Factory, baseUrl: String) = Retrofit.Builder()
    .callFactory(callFactory)
    .baseUrl(baseUrl)
    .addConverterFactory(Json(JsonConfiguration(strictMode = false))
        .asConverterFactory("application/json".toMediaType()))
    .build()

private fun okhttp(cache: Cache) = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .cache(cache)
    .build()