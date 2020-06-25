package com.todaysquare.disamplecode_koin.ui.example

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.todaysquare.disamplecode_koin.data.ExampleData
import com.todaysquare.disamplecode_koin.utils.PrintService

import org.koin.core.KoinComponent
import org.koin.core.inject

class ExampleViewModel(val printService: PrintService) : ViewModel() {
    val TAG = this.javaClass.simpleName

    private val _exampleLiveData: MutableLiveData<ExampleData> = MutableLiveData()
    val exampleData: LiveData<ExampleData>
        get() = _exampleLiveData

    fun requestData() {
        val exampleData = ExampleData().apply { value = 7 }

        _exampleLiveData.value = exampleData

    }

    override fun onCleared() {
        super.onCleared()
        Log.v(TAG, ">>> onCleared")

    }

    fun printHello() { printService.printHello() }

}