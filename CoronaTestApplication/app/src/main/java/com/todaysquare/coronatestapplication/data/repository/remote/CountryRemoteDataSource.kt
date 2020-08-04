package com.todaysquare.coronatestapplication.data.repository.remote

import com.todaysquare.coronatestapplication.data.network.response.CountryResponse

//  Remote -> 외부 DB or storage 이용한 데이터 가져올 때 사용 ex). retrofit, firebase etc...
//  Impl 에서 사용할 함수 정의
interface CountryRemoteDataSource {

    fun getCountry(success: (CountryResponse) -> Unit, fail: (Throwable) -> Unit)

}