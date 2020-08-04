package com.todaysquare.coronatestapplication.data.repository.local

import com.todaysquare.coronatestapplication.data.database.dao.CountryDao
import com.todaysquare.coronatestapplication.data.network.response.CountryResponse

class CountryLocalDataSourceImpl(private val countryDao: CountryDao) : CountryLocalDataSource {

    override fun insertCountry(country: CountryResponse) {
        countryDao.insertCountry(country)

    }

    override fun getCountry(): CountryResponse? = countryDao.getCountry()

}