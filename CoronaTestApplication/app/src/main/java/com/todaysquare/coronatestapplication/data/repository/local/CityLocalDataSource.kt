package com.todaysquare.coronatestapplication.data.repository.local

import com.todaysquare.coronatestapplication.data.database.entities.city.Korea

interface CityLocalDataSource {

    fun insertKorea(korea: Korea)
    fun getKorea(): Korea?

}