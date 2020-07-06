package com.todaysquare.publicmovieapisample.base

import android.app.Application

import com.todaysquare.publicmovieapisample.di.DaggerMovieComponent
import com.todaysquare.publicmovieapisample.di.MovieComponent

/**
 * 애플리케이션 사이의 공동 멤버 접근
 * 이후 context 를 통해 접근할 수 있다.
 */
class TheMoviePopularApp : Application() {
    companion object {
        lateinit var movieComponent: MovieComponent

    }

    override fun onCreate() {
        super.onCreate()

        // Dagger 에 의해 자동 생성된다.
        movieComponent = DaggerMovieComponent.builder().build()

    }
}