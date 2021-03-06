package com.todaysquare.coronatestapplication.data.repository.impl

import com.todaysquare.coronatestapplication.data.network.response.CityResponse
import com.todaysquare.coronatestapplication.data.repository.CityRepository
import com.todaysquare.coronatestapplication.data.repository.local.CityLocalDataSource
import com.todaysquare.coronatestapplication.data.repository.remote.CityRemoteDataSource
import com.todaysquare.coronatestapplication.utils.NetworkManager

//  로컬안쓰지만 연습 및 공부용 프로젝트라 만들어만 놔봤음( 사용할 필요가 없는 것 같아 사용은 안한다)
class CityRepositoryImpl(private val cityRemoteDataSource: CityRemoteDataSource,
    private val cityLocalDataSource: CityLocalDataSource, private val networkManager: NetworkManager
) : CityRepository {

    override fun getSearchCity(success: (CityResponse) -> Unit, fail: (Throwable) -> Unit) {
        if (networkManager.checkNetworkState()) {
            cityRemoteDataSource.getCity(
                success = {
                    success(it)

                },
                fail = {
                    fail(Throwable())

                })

        } else
            fail(Throwable())

    }
}