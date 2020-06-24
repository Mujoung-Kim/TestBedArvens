package com.todaysquare.iamportsamplecode.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import com.todaysquare.iamportsamplecode.data.network.Api
import com.todaysquare.iamportsamplecode.data.network.NetworkConnectionInterceptor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {
//    private val networkConnectionInterceptor = NetworkConnectionInterceptor(application)
    private val smsAuthApi by lazy { Api.createForImport(/*networkConnectionInterceptor*/) }

    private suspend fun iamPortGetAuthProfile(access_token: String, auth_user_imp_uid: String) {
        smsAuthApi.getAuthProfile(access_token, auth_user_imp_uid).apply{
            this.response.let{
                it.name.let { name -> }
                it.phone.let { phone -> }

            }
        }
    }

    fun iamPortPostAccessToken(imp_key: String, imp_secret: String, auth_user_imp_uid: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){
                    smsAuthApi.postAccessToken(imp_key, imp_secret).apply {
                        this.response.access_token.let {
                            iamPortGetAuthProfile(it, auth_user_imp_uid)

                        }
                    }
                }
            } catch (error: Throwable) {
                error.printStackTrace()

            }
        }
    }
}