package com.todaysquare.publicmovieapisample.di.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.todaysquare.publicmovieapisample.utils.Constants.Url.Companion.BASE_URL

import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import javax.inject.Singleton

@Module
class NetworkModule {

    /**
     * Retrofit 객체를 제공
     * @return Retrofit 객체 반환
     * 설명: Retrofit 빌더메서드인 baseUrl을 통해 URL 을 초기화하고 컨버터를 Moshi 로 지정
     * 코틀린의 코루틴을 같이 사용하기 위해 CoroutineCallAdapterFactory 지정
     * 이때는 Call 대신 Deferred 인스턴스를 반환해 사용할 수 있다.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    }
}