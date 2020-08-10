package com.todaysquare.functiontest.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.todaysquare.functiontest.data.databases.AppDatabases
import com.todaysquare.functiontest.data.databases.dao.CharacterDao
import com.todaysquare.functiontest.data.network.CharacterService
import com.todaysquare.functiontest.data.repositores.CharacterRepository
import com.todaysquare.functiontest.data.repositores.remote.CharacterRemoteDataSource
import com.todaysquare.functiontest.utils.Constants.Url.Companion.BASE_URL

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerRetrofit(gson: Gson) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson() = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit) =
        retrofit.create(CharacterService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabases.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(databases: AppDatabases) = databases.charterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource,
                          localDataSource: CharacterDao) =

        CharacterRepository(remoteDataSource, localDataSource)

}