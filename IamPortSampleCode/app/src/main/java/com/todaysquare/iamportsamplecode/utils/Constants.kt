package com.todaysquare.iamportsamplecode.utils

class Constants {
    class Url {
        companion object {
            const val BASE_URL = "https://api.iamport.kr/"

            const val GET_AUTH_PROFILE = "certifications/{imp_uid}"
            const val POST_GET_TOKEN = "users/getToken"

        }
    }

    class Param {
        companion object {
            const val CODE = "code"
            const val MESSAGE = "message"
            const val RESPONSE = "response"

            const val ACC_TOKEN = "access_token"
            const val NOW = "now"
            const val EXPIRED_AT = "expired_at"

            const val IMP_KEY = "imp_key"
            const val IMP_SECRET = "imp_secret"

            const val IMP_UID = "imp_uid"
            const val AUTHORIZATION = "Authorization"

            const val NAME = "name"
            const val PHONE = "phone"
            const val GENDER = "gender"
            const val BIRTH = "birth"
            const val CERTIFIED = "certified"
            const val CER_AT = "certified_at"

        }
    }

    class String {
        companion object {
            const val RESULT = "result"
            const val SUCCESS = "success"
            const val FAILURE = "failure"

            const val WEB_VIEW = "webview_url"
            const val AND_BRIDEGE = "AndroidBridge"

            const val SET_JAVA_SCRIPT = "SetJavaScriptEnabled"
            const val ON_PROGRESS = "onProgress"
            const val ERROR = "error"

        }
    }
}