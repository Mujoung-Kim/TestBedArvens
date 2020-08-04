package com.todaysquare.coronatestapplication.data.repository

import com.todaysquare.coronatestapplication.data.network.response.CountryResponse

interface CountryRepository {

    fun getSearchCountry(success: (CountryResponse) -> Unit, fail: (Throwable) -> Unit)

}