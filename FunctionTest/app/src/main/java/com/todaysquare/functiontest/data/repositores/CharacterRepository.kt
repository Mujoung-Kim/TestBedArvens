package com.todaysquare.functiontest.data.repositores

import com.todaysquare.functiontest.data.databases.dao.CharacterDao
import com.todaysquare.functiontest.data.repositores.remote.CharacterRemoteDataSource
import com.todaysquare.functiontest.utils.performGetOperation

import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDao) {

    fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacters(id) },
        saveCallResult = { localDataSource.insert(it) })

    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) })

}