package com.todaysquare.koinmovieexample.ui.movie

import android.util.Log
import androidx.lifecycle.*

import com.todaysquare.koinmovieexample.R
import com.todaysquare.koinmovieexample.data.MovieCollection
import com.todaysquare.koinmovieexample.data.MovieDataState
import com.todaysquare.koinmovieexample.data.network.ApiService
import com.todaysquare.koinmovieexample.utils.Event
import com.todaysquare.koinmovieexample.utils.SecretConstants.API_KEY

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
                Log.d("test", API_KEY)
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