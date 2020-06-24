package com.todaysquare.iamportexample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.todaysquare.iamportexample.data.repositories.MainRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(repository) as T
}