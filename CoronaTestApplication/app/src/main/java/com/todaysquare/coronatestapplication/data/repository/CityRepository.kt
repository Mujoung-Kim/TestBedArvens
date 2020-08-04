package com.todaysquare.coronatestapplication.data.repository

import com.todaysquare.coronatestapplication.data.network.response.CityResponse

interface CityRepository {

    fun getSearchCity(success: (CityResponse) -> Unit, fail: (Throwable) -> Unit)

}