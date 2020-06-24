package com.todaysquare.iamportexample.data.repositories

import android.util.Log

import com.todaysquare.iamportexample.R
import com.todaysquare.iamportexample.data.databases.entites.Token
import com.todaysquare.iamportexample.data.network.ApiService
import com.todaysquare.iamportexample.data.network.SafeApiRequest
import com.todaysquare.iamportexample.utils.Constants.Tag.Companion.MAIN_REPOS

class MainRepository(private val api: ApiService) : SafeApiRequest() {

    suspend fun getAccessToken(): Token {
        Log.d(MAIN_REPOS, "key - ${R.string.imp_key}")

        return apiRequest { api.postToken("${R.string.imp_key}", "${R.string.imp_secret}") }
    }
}