package com.todaysquare.daggermultibinding.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.todaysquare.daggermultibinding.data.models.DataModel
import com.todaysquare.daggermultibinding.data.network.ApiClient
import com.todaysquare.daggermultibinding.utils.*

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val context: Context, private val apiClient: ApiClient
) : ViewModel() {
    private val TAG = MainActivityViewModel::class.java.simpleName
    val dataResponse = MutableLiveData<DataModel>()

    init {
        addToDisposable(
            apiClient.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response -> Log.i(TAG, response.toString()) },
                    { error -> Log.i(TAG, error.toString()) }
                )
        )

    }

    override fun onCleared() {
        super.onCleared()
        removeAllDisposables()

    }
}