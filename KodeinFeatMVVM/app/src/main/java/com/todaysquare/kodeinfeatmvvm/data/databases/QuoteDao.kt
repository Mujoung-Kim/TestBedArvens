package com.todaysquare.kodeinfeatmvvm.data.databases

import androidx.lifecycle.LiveData

import com.todaysquare.kodeinfeatmvvm.data.databases.entites.Quote

interface QuoteDao {
    fun addQuote(quote: Quote)
    fun getQuotes(): LiveData<List<Quote>>

}