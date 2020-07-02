package com.todaysquare.koinmovieexample.utils

class Constants {
    class Url {
        companion object {
            //  BASE
            const val BASE_URL = "https://api.themoviedb.org/3/movie/"
            const val MOVIE_POSTER_BASE_URL = "https://pds.joins.com/news/component/htmlphoto_mmdata/201905/26/8ea065cf-5019-4de4-ab38-026b15a67e52.jpg"

            //  GET
            const val POPULAR = "popular"

            //  POST

        }
    }

    class Param {
        companion object {
            const val API_KEY = "api_key"
            const val PAGE = "page"
            const val RESULT = "results"
            const val ID = "id"
            const val ORIGINAL_TITLE = "original_title"
            const val POSTER_PATH = "poster_path"
            const val VOTE_AVERAGE = "vote_average"
            const val OVERVIEW = "overview"
            const val RELEASE_DATE = "release_date"

        }
    }
}