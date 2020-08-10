package com.todaysquare.functiontest.utils


import retrofit2.Response

import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null)
                    return Resource.success(body)

            }
            return error(" ${response.code()} ${response.message()}")

        } catch (error: Exception) {
            return error(error.message ?: error.toString())

        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)

        return Resource.error("Network call has failed for a following reason: $message")

    }
}