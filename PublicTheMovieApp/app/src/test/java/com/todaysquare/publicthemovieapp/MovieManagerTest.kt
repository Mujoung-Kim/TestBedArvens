package com.todaysquare.publicthemovieapp

import com.nhaarman.mockitokotlin2.*
import com.todaysquare.publicthemovieapp.data.network.responses.MovieDetailResponse
import com.todaysquare.publicthemovieapp.data.network.responses.MovieListResponse
import com.todaysquare.publicthemovieapp.data.network.TheMovieApi
import com.todaysquare.publicthemovieapp.ui.movie.MovieManager
import com.todaysquare.publicthemovieapp.utils.Constants.Secret.Companion.KEY_VALUE

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

import kotlin.test.assertNotNull

class MovieManagerTest {
    private var apiMock = mock<TheMovieApi>()
    val param = mapOf(
        "page" to "1",
        "api_key" to KEY_VALUE,
        "sort_by" to "popularity.desc",
        "language" to "ko"
    )

    //  response != null Test code.
    @Test
    fun `Success - check response is not null`() = testBlocking {
        val movieListResponse =
            MovieListResponse(
                1,
                listOf()
            )

        apiMock = mock {
            onBlocking { getMovieListCo(param) } doReturn movieListResponse

        }

        val movieManager =
            MovieManager(apiMock)
        val movieList = movieManager.getMovieList(param)

        assertNotNull(movieList)

    }

    //  movieList send Test code.
    @Test
    fun `Success - check received one movie info`() = testBlocking {
        val movieDetail =
            MovieDetailResponse(
                12,
                3.5f,
                "Title",
                "release_date",
                "poster_path",
                "overview"
            )
        val movie =
            MovieListResponse(
                1,
                listOf(movieDetail)
            )

        apiMock = mock {
            onBlocking { getMovieListCo(param) } doReturn movie

        }

        val movieManager =
            MovieManager(apiMock)
        val movieList = movieManager.getMovieList(param)

        assertNotNull(movieList)
        //  데이터 일치 확인
        assertEquals(movieDetail.release_date, movieList.results[0].release_date)
        assertEquals(movieDetail.title, movieList.results[0].title)

    }

    //  error situation Test code.
    @Test
    fun `Error - Exception received from service call`() {
        apiMock = mock {
            onBlocking { getMovieListCo(param) } doAnswer { throw Throwable() }

        }

        val movieManager =
            MovieManager(apiMock)

        assertFailsWith<Throwable> {
            runBlocking { movieManager.getMovieList(param) }

        }
    }

    private fun testBlocking(block: suspend CoroutineScope.() -> Unit) {
        runBlocking(Dispatchers.Default, block)

    }
}