package com.todaysquare.coronatestapplication.data.repository.local

import com.todaysquare.coronatestapplication.data.network.response.CountryResponse

//  Local -> 내부 DB or storage 이용한 데이터 가져올 때 사용
interface CountryLocalDataSource {

    fun insertCountry(country: CountryResponse)
    fun getCountry(): CountryResponse?

}