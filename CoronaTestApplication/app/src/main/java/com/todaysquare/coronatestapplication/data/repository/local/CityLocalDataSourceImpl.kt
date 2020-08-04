package com.todaysquare.coronatestapplication.data.repository.local

import com.todaysquare.coronatestapplication.data.database.dao.CityDao
import com.todaysquare.coronatestapplication.data.database.entities.city.Korea

class CityLocalDataSourceImpl(private val cityDao: CityDao) : CityLocalDataSource {

    override fun insertKorea(korea: Korea) {
        cityDao.insertKorea(korea)

    }

    override fun getKorea(): Korea? = cityDao.getKorea()

}