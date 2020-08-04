package com.todaysquare.coronatestapplication.data.repository.impl

import com.todaysquare.coronatestapplication.data.network.response.CountryResponse
import com.todaysquare.coronatestapplication.data.repository.CountryRepository
import com.todaysquare.coronatestapplication.data.repository.local.CountryLocalDataSource
import com.todaysquare.coronatestapplication.data.repository.remote.CountryRemoteDataSource
import com.todaysquare.coronatestapplication.utils.NetworkManager

/*
    *   Local & Remote 에서 data 을 가공한 후 viewModel 에서 사용할 기능 구현하는 부분
*/
class CountryRepositoryImpl(private val countryRemoteDataSource: CountryRemoteDataSource,
    private val countryLocalDataSource: CountryLocalDataSource, private val networkManager: NetworkManager
) : CountryRepository {

    override fun getSearchCountry(success: (CountryResponse) -> Unit, fail: (Throwable) -> Unit) {

        if (networkManager.checkNetworkState())
            countryRemoteDataSource.getCountry(
                success = {
                    countryLocalDataSource.insertCountry(it)
                    success(it)

                }, fail = {
                    fail(Throwable())

                })

        else {
            countryLocalDataSource.getCountry()?.let { success(it) }
            fail(Throwable())

        }
    }
}