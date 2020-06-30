package com.todaysquare.dagger2_example.data.repositories

import com.todaysquare.dagger2_example.data.models.Balance
import com.todaysquare.dagger2_example.service.BalanceService

import io.reactivex.Single

import javax.inject.Inject

class TransactionRepository @Inject constructor(private val balanceService: BalanceService) {

    fun getTransactionRepository(api_key: String, type: String): Single<Balance> =
        balanceService.getTransaction(api_key, type)

}