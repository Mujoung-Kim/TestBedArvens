package com.todaysquare.coronatestapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.todaysquare.coronatestapplication.data.network.response.CountryResponse

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(country: CountryResponse)

    @Query("select * from country limit 1")
    fun getCountry(): CountryResponse?

}