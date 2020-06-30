package com.todaysquare.dagger2_example.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.todaysquare.dagger2_example.data.models.Balance
import com.todaysquare.dagger2_example.data.repositories.WithDrawCoinRepository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class AccountViewModel @Inject constructor(private val withDrawCoinRepository: WithDrawCoinRepository
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val withDrawCoinRepositoryLiveData = MutableLiveData<Balance>()
    private val isLoading = MutableLiveData<Boolean>()
    private val errorDisplay = MutableLiveData<String>()

    private fun loadData(api_key: String, amount: String, to_addresses: String) {
        isLoading.value = true
        errorDisplay.value = ""
        disposable.add(withDrawCoinRepository.getWithDrawCoinRepository(api_key, amount, to_addresses)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                data ->
                isLoading.value = false
                getWithDrawCoinRepositoryLiveData(api_key, amount, to_addresses).value = data

            }, {
                error ->
                isLoading.value = false
                errorDisplay.value = error.toString()

            })
        )

    }

    fun getWithDrawCoinRepositoryLiveData(api_key: String, amount: String,
        to_addresses: String): MutableLiveData<Balance> {

        loadData(api_key, amount, to_addresses)

        return withDrawCoinRepositoryLiveData

    }

    fun isLoading(): LiveData<Boolean> = isLoading

    fun errorDisplay(): LiveData<String> = errorDisplay

}