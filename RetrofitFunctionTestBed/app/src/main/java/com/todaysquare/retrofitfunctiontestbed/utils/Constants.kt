package com.todaysquare.retrofitfunctiontestbed.utils

import android.Manifest

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

            //  TYPE PERMISSION VALUES
            val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
            val STORAGE_PERMISSION = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            const val FLAG_PERM_CAMERA = 98
            const val FLAG_PERM_STORAGE = 99
            const val FLAG_REQ_CAMERA = 101
            const val FLAG_REQ_STORAGE = 102

        }
    }
}