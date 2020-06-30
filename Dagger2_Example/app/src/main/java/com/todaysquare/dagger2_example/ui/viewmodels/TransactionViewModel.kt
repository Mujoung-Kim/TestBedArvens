package com.todaysquare.dagger2_example.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.todaysquare.dagger2_example.data.models.Balance
import com.todaysquare.dagger2_example.data.repositories.TransactionRepository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class TransactionViewModel @Inject constructor(private val transactionRepository: TransactionRepository
): ViewModel() {

    private val disposable = CompositeDisposable()
    private val transactionRepositoryLiveData = MutableLiveData<Balance>()
    private val isLoading = MutableLiveData<Boolean>()
    private val errorMessage = MutableLiveData<String>()

    private fun loadData(api_key: String, type: String) {
        isLoading.value = true
        disposable.add(transactionRepository.getTransactionRepository(api_key, type)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                    data ->
                getTransactionMutableLiveData(api_key, type).value = data
                isLoading.value = false

            }, { error ->
                isLoading.value = false
                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                Log.e("Transaction", error.message)

            })
        )
    }

    fun getTransactionMutableLiveData(api_key: String, type: String): MutableLiveData<Balance> {
        loadData(api_key, type)

        return transactionRepositoryLiveData

    }

    fun isLoading(): LiveData<Boolean> = isLoading

    fun errorMessage(): LiveData<String> = errorMessage

}