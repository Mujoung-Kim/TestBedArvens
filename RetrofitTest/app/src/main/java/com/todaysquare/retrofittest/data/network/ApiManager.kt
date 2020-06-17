package com.todaysquare.retrofittest.data.network

import android.content.Context
import retrofit2.Call
import java.lang.reflect.Type

class ApiManager {
    companion object {
        val instance: ApiManager get() = Singleton.MANAGER

    }
    private var apiService: ApiService? = null
    val apiManager: ApiManager get() = instance

    private object Singleton {
        val MANAGER = ApiManager()

    }

//    fun getApiService(context: Context): ApiService? {
//        initApiSetting(context)
//        return apiService
//
//    }
//
//    fun getApiResponse(type: Type, request: Call<String?>, listener: IHTTPListener) {
//
//    }
}