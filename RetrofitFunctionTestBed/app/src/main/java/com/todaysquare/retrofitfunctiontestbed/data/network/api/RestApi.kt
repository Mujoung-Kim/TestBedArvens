package com.todaysquare.retrofitfunctiontestbed.data.network.api

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.todaysquare.retrofitfunctiontestbed.data.model.Funding
import com.todaysquare.retrofitfunctiontestbed.data.network.ApiServiceInterface
import com.todaysquare.retrofitfunctiontestbed.data.network.response.ResponseWrapper
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Url.Companion.BASE_URL

import okhttp3.Interceptor
import okhttp3.OkHttpClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

import timber.log.Timber

//  Retrofit body
class RestApi {
    private val apiServiceInterface: ApiServiceInterface
    private val commonNetworkInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val newRequest = chain.request().newBuilder()
                .addHeader("token", SPUtil.accessToken).build()
            val response = chain.proceed(newRequest)
            val rawJson = response.body()?.string() ?: "{}"
            val type = object : TypeToken<ResponseWrapper<*>>() {}.type
            val res = try {
                val r = Gson().fromJson<ResponseWrapper<*>>(rawJson, type)
                    ?: throw JsonSyntaxException("Parse Fail")

                if (!r.success)
                    ResponseWrapper<Any>(-999, false, "Server Logic fail : ${r.message}", null)
                else
                    r

            } catch (error: JsonSyntaxException) {
                ResponseWrapper<Any>(-999, false, "json parsing fail : $error", null)

            } catch (t: Throwable) {
                ResponseWrapper<Any>(-999, false, "unknown error : $t", null)

            }

            return response

        }
    }

    init {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(commonNetworkInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        apiServiceInterface = retrofit.create(ApiServiceInterface::class.java)

    }

    //  Create a retrofit function for you to use.
    fun testBed() {
        apiServiceInterface.listFundingHistories()
            .enqueue(object : Callback<ResponseWrapper<List<Funding>>>{
                override fun onFailure(call: Call<ResponseWrapper<List<Funding>>>, t: Throwable) {
                    Timber.e("network request failure.")

                }

                override fun onResponse(call: Call<ResponseWrapper<List<Funding>>>,
                    response: Response<ResponseWrapper<List<Funding>>>) {

                    if (response.isSuccessful)
                        if (response.body()?.success == true)
                            if (response.body()?.data != null) Timber.e("we want dis data: ${response.body()!!.data!!}")
                            else Timber.e("network request failure. !")
                        else Timber.e("network request failure. !!")
                    else Timber.e("network request failure. !!!")

                }
            })

    }
}