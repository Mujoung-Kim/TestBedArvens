package com.todaysquare.iamportexample.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.todaysquare.iamportexample.R
import com.todaysquare.iamportexample.data.repositories.MainRepository
import com.todaysquare.iamportexample.utils.Constants.Tag.Companion.MAIN_MODEL

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

   /* fun getAccessToken() {
        Log.d(MAIN_MODEL,
            "key - ${context.getString(R.string.imp_key)},\nsecret - ${context.getString(R.string.imp_secret)}")
        viewModelScope.launch(Dispatchers.IO) {
            api.postToken(context.getString(R.string.imp_key), context.getString(R.string.imp_secret))

            Log.d(MAIN_MODEL, "successfully")

        }
    }*/

    suspend fun getAccessToken() = repository.getAccessToken()

}