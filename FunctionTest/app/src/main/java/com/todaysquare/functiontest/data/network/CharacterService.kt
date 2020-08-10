package com.todaysquare.functiontest.data.network

import com.todaysquare.functiontest.data.databases.entites.Character
import com.todaysquare.functiontest.data.databases.entites.CharacterList
import com.todaysquare.functiontest.utils.Constants.Param.Companion.PARAM_ID
import com.todaysquare.functiontest.utils.Constants.Url.Companion.GET_CHARACTER
import com.todaysquare.functiontest.utils.Constants.Url.Companion.GET_CHARACTER_ID

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET(GET_CHARACTER)
    suspend fun getAllCharacters(): Response<CharacterList>

    @GET(GET_CHARACTER_ID)
    suspend fun getCharacter(@Path(PARAM_ID) id: Int): Response<Character>

}