package com.todaysquare.iamportexample.data.repositories

//import com.todaysquare.iamportexample.R
//import com.todaysquare.iamportexample.data.databases.AppDatabase
//import com.todaysquare.iamportexample.data.network.ApiService
//import com.todaysquare.iamportexample.data.network.SafeApiRequest
//import com.todaysquare.iamportexample.data.network.responses.TokenResponse
//
//class TokenRepository(
//    private val api: ApiService,
//    private val database: AppDatabase
//) : SafeApiRequest() {
//
//    suspend fun getTokens(): TokenResponse =
//        apiRequest { api.getToken("${R.string.imp_key}", "${R.string.imp_secret}") }
//
//    fun getAll() = database.getTokenDao().getTokens()
//
//}