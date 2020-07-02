package com.todaysquare.koinmovieexample.di.modules

import com.todaysquare.koinmovieexample.ui.movie.MovieViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module { viewModel { MovieViewModel(get()) } }