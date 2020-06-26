package com.todaysquare.kodeinfeatmvvm.data.repositories

import androidx.lifecycle.LiveData

import com.todaysquare.kodeinfeatmvvm.data.databases.entites.Quote

interface QuoteRepository {
    fun addQuote(quote: Quote)
    fun getQuotes(): LiveData<List<Quote>>

}