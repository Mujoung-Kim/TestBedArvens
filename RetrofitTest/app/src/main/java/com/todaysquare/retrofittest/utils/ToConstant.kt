package com.todaysquare.retrofittest.utils

class ToConstant {
    companion object {
        const val FORM_DATA_BOUNDARY = "983xjdf83hsd2kdfldf"

    }

    class Url {
        companion object {
            const val BASE_URL = "https://api.iamport.kr/"
            const val GET_ = ""
            const val POST_GET_TOKEN = "users/getToken"

            const val GET_ACTIVITY_EXERCISE_SEARCH = "api/activity/exercise/search"
            const val POST_ACTIVITY_EXERCISE_RECORD_INPUT = "api/activity/exercise/input/save"
            const val POST_PROFILE_IMAGE_UPLOAD = "api/profile/update"

        }
    }

    class Param {
        companion object {
            const val CODE = "code"
            const val MESSAGE = "message"
            const val RESPONSE = "response"

            const val PROFILE_IMAGE = "profile_image"
            const val GENDER = "gender"
            const val NICK_NAME = "nick_name"
            const val WEIGHT = "weight"
            const val HEIGHT = "height"

            const val HEADER_MOBILE_PLATFORM = "header_mobile_platform"
            const val HEADER_APP_VERSION = "header_app_version"

        }
    }
}