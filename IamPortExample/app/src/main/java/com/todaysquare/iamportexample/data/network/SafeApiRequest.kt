package com.todaysquare.iamportexample.data.network

import com.todaysquare.iamportexample.utils.ApiException
import com.todaysquare.iamportexample.utils.Constants.Param.Companion.MESSAGE

import org.json.JSONException
import org.json.JSONObject

//import retrofit2.Response
//
//import java.lang.StringBuilder
//
//abstract class SafeApiRequest {
//    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
//        val response = call.invoke()
//
//        if (response.isSuccessful) return response.body()!!
//        else {
//            val error = response.errorBody()?.toString()
//            val message = StringBuilder()
//
//            error?.let {
//                try {
//                    message.append(JSONObject(it).getString(MESSAGE))
//
//                } catch (error: JSONException) { }
//                message.append("\n")
//
//            }
//            message.append("Error code : ${response.code()}")
//            throw ApiException(message.toString())
//
//        }
//    }
//}