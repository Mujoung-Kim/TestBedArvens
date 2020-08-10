package com.todaysquare.functiontest.data.databases.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.todaysquare.functiontest.data.databases.entites.Character

@Dao
interface CharacterDao {

    @Query("select * from characters")
    fun getAllCharacters(): LiveData<List<Character>>

    @Query("select * from characters where id = :id")
    fun getCharacter(id: Int): LiveData<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(character: List<Character>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

}