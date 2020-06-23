package com.todaysquare.iamportexample.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

import com.todaysquare.iamportexample.utils.NoInternetException

import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Check this network connection.")

        return chain.proceed(chain.request())

    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        var result =false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager?.let {
                it.getNetworkCapabilities(connectivityManager.activeNetwork).apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false

                    }
                }
            }
            return result

        } else {
            connectivityManager?.activeNetworkInfo.also {
                return it != null && it.isConnected

            }
        }
    }
}