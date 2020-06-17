package com.todaysquare.retrofittest.data.network

class ToConstant {
    companion object {
        const val FORM_DATA_BOUNDARY = "983xjdf83hsd2kdfldf"

    }

    class Url {
        companion object {
            const val BASE_URL = "https://api.iamport.kr/"
            const val GET_ = ""
            const val POST_GET_TOKEN = "users/getToken"

        }
    }

    class Param {
        companion object {
            const val CODE = "code"
            const val MESSAGE = "message"
            const val RESPONSE = "response"

        }
    }
}