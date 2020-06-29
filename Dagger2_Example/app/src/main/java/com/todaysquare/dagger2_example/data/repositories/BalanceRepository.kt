package com.todaysquare.dagger2_example.data.repositories

import com.todaysquare.dagger2_example.data.models.Balance
import com.todaysquare.dagger2_example.service.BalanceService

import io.reactivex.Single

import javax.inject.Inject

class BalanceRepository @Inject constructor(private val balanceService: BalanceService) {

    fun getBalance_Repository(apiKey: String): Single<Balance> = balanceService.getBalance(apiKey)

}