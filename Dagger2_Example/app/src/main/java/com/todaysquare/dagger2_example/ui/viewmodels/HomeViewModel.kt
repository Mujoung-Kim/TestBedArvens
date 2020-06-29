package com.todaysquare.dagger2_example.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.todaysquare.dagger2_example.data.models.Balance
import com.todaysquare.dagger2_example.data.repositories.BalanceRepository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class HomeViewModel @Inject constructor(private val balanceRepository: BalanceRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val balanceMutableLiveData = MutableLiveData<Balance>()
    private val isLoading = MutableLiveData<Boolean>()
    private val errorDisplay = MutableLiveData<String>()

    private fun getBalanceMutableLiveData(apiKey: String): MutableLiveData<Balance> {
        loadData(apiKey)

        return balanceMutableLiveData

    }

    private fun loadData(apiKey: String) {
        isLoading.value = true
        disposable.add(balanceRepository.getBalance_Repository(apiKey)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ data ->
                getBalanceMutableLiveData(apiKey).value = data
                isLoading.value = false
            }, { error ->
                isLoading.value = false
                Log.e("Home", error.toString())
            })
        )

    }

    fun isLoading(): LiveData<Boolean> = isLoading

    fun errorDisplay(): LiveData<String> = errorDisplay

}