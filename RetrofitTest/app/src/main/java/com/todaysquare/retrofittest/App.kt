package com.todaysquare.retrofittest

import android.app.Application

import com.google.gson.Gson
import com.todaysquare.retrofittest.data.network.ApiManager


class App : Application() {
    private var apiManager: ApiManager? = null
    private var gson: Gson? = null

    private object Singleton {
        val instance: App = App()

    }

    fun getInstance(): App = Singleton.instance

    fun getGson(): Gson? {
        if (gson == null) gson = Gson()
        return gson

    }

    fun getApiManager(): ApiManager? {
        if (apiManager == null) apiManager = ApiManager()
        return apiManager

    }
}