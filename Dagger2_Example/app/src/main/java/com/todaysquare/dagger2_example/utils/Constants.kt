package com.todaysquare.dagger2_example.utils

class Constants {
    class Url {
        companion object {
            const val BASE_URL = "https://block.io/api/v2/"
            const val CRYPTO_URL = "https://min-api.cryptocompare.com/"

            //  GET
            const val GET_BALANCE = "get_balance/"
            const val GET_TRANSACTIONS = "get_transactions/"
            const val GET_CRYPTO = "data/v2/histohour?fsym=BTC&tsym=USD&limit=10"

            //  POST
            const val POST_WITHDRAW = "withdraw/"

        }
    }

    class Param {
        companion object {
            const val API_KEY = "api_key"
            const val AMOUNTS = "amounts"
            const val TO_ADDRESSES = "to_addresses"
            const val TYPE = "type"

        }
    }

//    class String {
//        companion object {
//
//        }
//    }
}