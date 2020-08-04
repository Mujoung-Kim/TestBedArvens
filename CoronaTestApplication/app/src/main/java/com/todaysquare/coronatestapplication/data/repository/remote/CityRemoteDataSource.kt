package com.todaysquare.coronatestapplication.data.repository.remote

import com.todaysquare.coronatestapplication.data.network.response.CityResponse

interface CityRemoteDataSource {

    fun getCity(success: (CityResponse) -> Unit, fail: (Throwable) -> Unit)

}