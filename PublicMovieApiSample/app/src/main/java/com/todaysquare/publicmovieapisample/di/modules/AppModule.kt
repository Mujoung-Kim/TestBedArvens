package com.todaysquare.publicmovieapisample.di.modules

import android.app.Application
import android.content.Context

import com.todaysquare.publicmovieapisample.base.TheMoviePopularApp

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class AppModule(val app: TheMoviePopularApp) {

    /**
     * provideContext()는 Application 의 의존성 객체인 컨텍스트를 제공
     * @Singleton 은 Dagger API 는 아니고 javax.annotation 패키지에 포함된 어노테이션으로
     * 인스턴스가 오로지 하나 이어야만 한다는 것
     */
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): Application = app

}