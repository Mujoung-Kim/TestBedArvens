package com.todaysquare.koinmovieexample.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.todaysquare.koinmovieexample.R
import com.todaysquare.koinmovieexample.data.MovieCollection
import com.todaysquare.koinmovieexample.data.MovieDataState
import com.todaysquare.koinmovieexample.data.network.ApiService
import com.todaysquare.koinmovieexample.utils.Constants.Param.Companion.API_KEY
import com.todaysquare.koinmovieexample.utils.Event

import kotlinx.coroutines.launch

class MovieViewModel constructor(private val apiService: ApiService) : ViewModel() {
    private val _uiState = MutableLiveData<MovieDataState>()
    val uiState: LiveData<MovieDataState> get() = _uiState

    init {
        retrieveMovies()

    }

    private fun retrieveMovies() {
        viewModelScope.launch {
            kotlin.runCatching {
                emitUiState(showProgress = true)
                apiService.popularMovies(apiKey = API_KEY)

            }.onSuccess { emitUiState(movies = Event(it.movies))
            }.onFailure {
                it.printStackTrace()
                emitUiState(error = Event(R.string.internet_failure_error))

            }
        }
    }

    private fun emitUiState(showProgress: Boolean = false, movies: Event<List<MovieCollection.Movie>>? = null,
        error: Event<Int>? = null) {
        val dataState = MovieDataState(showProgress, movies, error)

        _uiState.value = dataState

    }
}