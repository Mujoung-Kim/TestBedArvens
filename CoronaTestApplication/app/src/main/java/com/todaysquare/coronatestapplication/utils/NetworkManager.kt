package com.todaysquare.coronatestapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkManager(private val context: Context) {

    fun checkNetworkState(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activityNetwork =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activityNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activityNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false

            }
        } else {
            val networkInformation = connectivityManager.activeNetworkInfo ?: return false

            return networkInformation.isConnected

        }
    }
}