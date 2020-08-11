package com.todaysquare.functiontest.data.databases.entites

//  {} Json 전체 파일
//  BASE_URL https://rickandmortyapi.com/api/character/?page=2
data class CharacterList(
    val info: Info,
    val results: List<Character>

)