package com.todaysquare.coronatestapplication.ui.country

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.todaysquare.coronatestapplication.data.network.response.CountryResponse
import com.todaysquare.coronatestapplication.data.repository.CountryRepository
import com.todaysquare.coronatestapplication.utils.Constants.Param.Companion.TEST

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>(false)
    private val _countryResponse = MutableLiveData<CountryResponse>()
    private val _toastMsg = MutableLiveData<String>()

    val isLoading: LiveData<Boolean> get() = _isLoading
    val countryResponse: LiveData<CountryResponse> get() = _countryResponse
    val toastMsg: LiveData<String> get() = _toastMsg

    fun requestCountry(){
        _isLoading.value = true
        countryRepository.getSearchCountry(
            success = {
                Log.d(javaClass.simpleName + TEST, "" + it)
                _countryResponse.value = it
                _isLoading.value = false

            }, fail = {
                Log.d(javaClass.simpleName + TEST, "" + it)
                _toastMsg.value = it.toString()
                _isLoading.value = false

            })

    }
}