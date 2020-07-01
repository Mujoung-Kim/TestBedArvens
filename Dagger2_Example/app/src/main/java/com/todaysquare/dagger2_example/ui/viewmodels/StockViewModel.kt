package com.todaysquare.dagger2_example.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.todaysquare.dagger2_example.data.cryptomodels.CryptoModel
import com.todaysquare.dagger2_example.service.CryptoInterface
import com.todaysquare.dagger2_example.utils.Constants.Url.Companion.CRYPTO_URL

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class StockViewModel: ViewModel() {
    private val mutableCryptoData = MutableLiveData<CryptoModel>()

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(CRYPTO_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val cryptoInterface: CryptoInterface = retrofit.create(CryptoInterface::class.java)
        val call: Call<CryptoModel> = cryptoInterface.getData()

        call.enqueue(object : Callback<CryptoModel> {
            override fun onFailure(call: Call<CryptoModel>, t: Throwable) {
                Log.d("Crypto", t.message.toString())

            }

            override fun onResponse(call: Call<CryptoModel>, response: Response<CryptoModel>) {
                mutableCryptoData.value = response.body()

            }
        })

    }

    fun getMutableCryptoData(): LiveData<CryptoModel> {
        loadData()

        return mutableCryptoData

    }
}