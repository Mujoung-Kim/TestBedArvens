package com.todaysquare.iamportexample.utils

class Constants {
    class Url {
        companion object {
            const val BASE_URL = "https://api.iamport.kr/"
            const val POST_GET_TOKEN = "users/getToken"

        }
    }

    class Param {
        companion object {
            const val CODE = "code"
            const val MESSAGE = "message"
            const val RESPONSE = "response"

            const val IMP_KEY = "imp_key"
            const val IMP_SECRET = "imp_secret"
            const val ACC_TOKEN = "access_token"

        }
    }
}