package com.todaysquare.kodeinfeatmvvm.ui.quotes

import androidx.lifecycle.ViewModel

import com.todaysquare.kodeinfeatmvvm.data.databases.entites.Quote
import com.todaysquare.kodeinfeatmvvm.data.repositories.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)

    fun getQuotes() = quoteRepository.getQuotes()

}