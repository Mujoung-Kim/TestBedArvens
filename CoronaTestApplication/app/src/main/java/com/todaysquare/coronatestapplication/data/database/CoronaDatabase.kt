package com.todaysquare.coronatestapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.todaysquare.coronatestapplication.data.database.dao.CityDao
import com.todaysquare.coronatestapplication.data.database.dao.CountryDao
import com.todaysquare.coronatestapplication.data.database.entities.city.Korea
import com.todaysquare.coronatestapplication.data.network.response.CountryResponse

@Database(entities = [CountryResponse::class, Korea::class], version = 1, exportSchema = false)
abstract class CoronaDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    abstract fun cityDao(): CityDao

}