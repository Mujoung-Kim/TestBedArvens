package com.todaysquare.coronatestapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.todaysquare.coronatestapplication.data.database.entities.city.Korea

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKorea(korea: Korea)

    @Query("SELECT * FROM korea LIMIT 1")
    fun getKorea(): Korea?

}