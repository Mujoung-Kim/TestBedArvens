package com.todaysquare.coronatestapplication.di.modules

import com.todaysquare.coronatestapplication.ui.city.CityViewModel
import com.todaysquare.coronatestapplication.ui.country.CountryViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountryViewModel(get()) }
    viewModel { CityViewModel(get()) }

}