package com.todaysquare.iamportexample.data.databases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.todaysquare.iamportexample.data.databases.entites.Token

@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(token: Token)

    @Query("select * from Tokens")
    fun getTokens(): LiveData<List<Token>>

}