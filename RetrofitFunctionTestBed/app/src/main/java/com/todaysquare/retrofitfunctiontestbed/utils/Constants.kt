package com.todaysquare.retrofitfunctiontestbed.utils

//  Globally used constants.
object Constants {
    class Url {
        companion object {
            //  BASE
            const val BASE_URL = "https://random.api.server.com/api/"
            const val BASE_GIT = "https://api.github.com/"
            const val BASE_SEO = "http://openAPI.seoul.go.kr:8088/"

            //  GET
            const val USER_REPO = "users/Kotlin/repos"
            const val LIBR_INFO = "{api_key}/json/SeoulPublicLibraryInfo/1/200"

            //  POST

        }
    }

    class Param {
        companion object {
            const val LIB_KEY = "api_key"

        }
    }
}