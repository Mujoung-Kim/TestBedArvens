package com.todaysquare.coronatestapplication.data.repository.remote

import com.todaysquare.coronatestapplication.data.network.ApiInterface
import com.todaysquare.coronatestapplication.data.network.response.CountryResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

//  DataSource 에서 데이터 가져와서 사용할 함수 재정의 -> 직접 기능 구현하는 부분
class CountryRemoteDataSourceImpl(private val apiInterface: ApiInterface) : CountryRemoteDataSource {

    override fun getCountry(success: (CountryResponse) -> Unit, fail: (Throwable) -> Unit) {
        val countryCall = apiInterface.getCountryInfo()

        countryCall.enqueue(object : Callback<CountryResponse> {
            override fun onFailure(call: Call<CountryResponse>, t: Throwable) {
                fail(t)

            }

            override fun onResponse(call: Call<CountryResponse>, response: Response<CountryResponse>) {
                with(response) {
                    val body = body()

                    if (isSuccessful && body != null)
                        success(body)
                    else
                        fail(HttpException(this))

                }
            }
        })

    }
}