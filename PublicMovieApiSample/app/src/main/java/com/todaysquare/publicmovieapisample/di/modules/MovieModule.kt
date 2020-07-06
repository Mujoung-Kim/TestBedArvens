package com.todaysquare.publicmovieapisample.di.modules

import com.todaysquare.publicmovieapisample.data.network.TheMovieApi
import com.todaysquare.publicmovieapisample.data.network.TheMovieRestApi
import com.todaysquare.publicmovieapisample.data.network.TheMovieService

import dagger.Module
import dagger.Provides

import retrofit2.Retrofit

import javax.inject.Singleton

@Module
class MovieModule {

    /**
     * 이 메서드는 NetworkModule 의 provideRetrofit()으로 부터 Retrofit 객체가 매개변수로
     * 전달되었는데 이 메서드 내부에서 Retrofit 객체가 생성되지 않고 Dagger 에 의해
     * 자동 제공/주입될 것입니다.
     */
    @Provides
    @Singleton
    fun provideTheMovieService(retrofit: Retrofit): TheMovieService =
        retrofit.create(TheMovieService::class.java)

    /**
     * 웹서비스의 API 를 제공하기 위해 provideTheMovieService()로부터 전달받은
     * Retrofit 객체를 매개변수로 지정해 자동 제공될 것입니다.
     */
    @Provides
    @Singleton
    fun provideTheMovieApi(theMovieService: TheMovieService): TheMovieApi =
        TheMovieRestApi(theMovieService)
    /**
     * MovieManager 는 생성자의 @Inject 어노테이션으로 Dagger 에 의해 자동적으로 제공되기 때문에
     * 제공자 메서드를 추가 하지 않았습니다.
     */

}