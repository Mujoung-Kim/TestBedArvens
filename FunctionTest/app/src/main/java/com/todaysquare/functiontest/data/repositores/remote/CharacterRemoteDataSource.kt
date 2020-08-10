package com.todaysquare.functiontest.data.repositores.remote

import com.todaysquare.functiontest.data.network.CharacterService
import com.todaysquare.functiontest.utils.BaseDataSource

import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService) : BaseDataSource() {

    suspend fun getCharacters() =
        getResult { characterService.getAllCharacters() }

    suspend fun getCharacters(id: Int) =
        getResult { characterService.getCharacter(id) }

}