package com.todaysquare.dagger2_example.data.repositories

import com.todaysquare.dagger2_example.data.models.Balance
import com.todaysquare.dagger2_example.service.BalanceService

import io.reactivex.Single

import javax.inject.Inject

class WithDrawCoinRepository @Inject constructor(private val balanceService: BalanceService) {

    fun getWithDrawCoinRepository(api_key: String, amount: String, to_addresses: String): Single<Balance> =
        balanceService.withDrawCoin(api_key, amount, to_addresses)

}