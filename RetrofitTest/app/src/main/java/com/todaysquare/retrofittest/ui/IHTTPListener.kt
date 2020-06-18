package com.todaysquare.retrofittest.ui

interface IHTTPListener {
    fun onSuccess(result: Any?)

    fun onFail(code: Int, message: String?, result: Any?)

}