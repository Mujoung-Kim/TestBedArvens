package com.todaysquare.publicmovieapisample

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock

import com.todaysquare.publicmovieapisample.data.network.TheMovieApi
import com.todaysquare.publicmovieapisample.data.network.responses.MovieDetailResponse
import com.todaysquare.publicmovieapisample.data.network.responses.MovieListResponse
import com.todaysquare.publicmovieapisample.ui.movies.MovieManager
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.API_KEY
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.LANGUAGE
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.PAGE
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.SORT_BY
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.SORT_POPULARITY
import com.todaysquare.publicmovieapisample.utils.Constants.Secret.Companion.KEY_VALUE

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

import org.junit.Test

class MovieManagerTest {
    private var apiMock = mock<TheMovieApi>()
    val param = mapOf(
        PAGE to "1",
        API_KEY to KEY_VALUE,
        SORT_BY to SORT_POPULARITY,
        LANGUAGE to "ko")

    @Test
    fun `Success - check response is not null`() = testBlocking {
        val movieListResponse = MovieListResponse(1, listOf())

        apiMock = mock { onBlocking { getMovieListCo(param) } doReturn movieListResponse }

        val movieManager = MovieManager(apiMock)
        val movieList = movieManager.getMovieList(param)

        assertNotNull(movieList)

    }

    @Test
    fun `Success - checks received one movie info`() = testBlocking {
        val movieDetail = MovieDetailResponse(
            123,
            3.5f,
            "Title",
            "release_data",
            "poster_path",
            "overview"
        )
        val movie = MovieListResponse(1, listOf(movieDetail))

        apiMock = mock { onBlocking { getMovieListCo(param) } doReturn movie }

        val movieManager = MovieManager(apiMock)
        val movieList = movieManager.getMovieList(param)

        assertNotNull(movieList)
        assertEquals(movieDetail.release_data, movieList.results[0].release_data)
        assertEquals(movieDetail.title, movieList.results[0].title)

    }

    @Test
    fun `Error - Exception received from service call`() {
        apiMock = mock { onBlocking { getMovieListCo(param) } doAnswer { throw Throwable() } }

        val movieManager = MovieManager(apiMock)

        assertFailsWith<Throwable> { runBlocking { movieManager.getMovieList(param) } }

    }

    private fun testBlocking(block: suspend CoroutineScope.() -> Unit) {
        runBlocking(Dispatchers.Default, block)

    }
}