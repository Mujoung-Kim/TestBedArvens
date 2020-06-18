package com.todaysquare.retrofittest.data.network

import android.content.Context
import android.util.Log

import com.todaysquare.retrofittest.App
import com.todaysquare.retrofittest.data.network.responses.BaseResponseModel
import com.todaysquare.retrofittest.ui.IHTTPListener
import com.todaysquare.retrofittest.utils.ToConstant.Param.Companion.HEADER_APP_VERSION
import com.todaysquare.retrofittest.utils.ToConstant.Param.Companion.HEADER_MOBILE_PLATFORM
import com.todaysquare.retrofittest.utils.ToConstant.Url.Companion.BASE_URL

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Logger

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.lang.reflect.Type


class ApiManager {
    companion object {
        val instance: ApiManager get() = Singleton.MANAGER

    }
    private var apiService: ApiService? = null
    val apiManager: ApiManager get() = instance
    val app = App()

    private object Singleton {
        val MANAGER = ApiManager()

    }

    fun getApiService(context: Context?): ApiService? {
        initApiSetting(context!!)
        return apiService

    }

    fun getApiResponse(type: Type, request: Call<String?>, listener: IHTTPListener) {
        request.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if ((200 == response.code() || 400 == response.code()
                            || 403 == response.code()) && response.body() != null) {

                    val result: Any? = app.getInstance()?.getGson()
                        ?.fromJson(response.body().toString(), type)
                    val model: BaseResponseModel = result as BaseResponseModel

                    if (0 == model.code) listener.onSuccess(model)
                    else listener.onFail(model.code, model.message, model)

                } else listener.onFail(-1, "", null)

            }

            override fun onFailure(call: Call<String?>, t: Throwable) {

            }
        })
    }

    private fun initApiSetting(context: Context) {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        val interceptor = Interceptor { chain ->
            val request = chain.request()
            val headers = Headers.Builder()
                .add(HEADER_MOBILE_PLATFORM, "android")
                .add(HEADER_APP_VERSION, "111")
                .add("Content-Type", "application/json;charset=utf-8")
                .build()
            val newRequest = request.newBuilder().headers(headers).build()

            chain.proceed(newRequest)
        }

        val logInterceptor = HttpLoggingInterceptor(Logger { message ->
            Log.d("The end", "retrofit : $message")

        })

        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()

        builder.interceptors().add(interceptor)
        builder.interceptors().add(logInterceptor)

        val client = builder.build()

        retrofitBuilder.client(client)

        val retrofit = retrofitBuilder.build()

        apiService = retrofit.create(ApiService::class.java)

    }
}