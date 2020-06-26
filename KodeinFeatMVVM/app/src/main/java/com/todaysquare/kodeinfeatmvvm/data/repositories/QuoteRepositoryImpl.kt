package com.todaysquare.kodeinfeatmvvm.data.repositories

import com.todaysquare.kodeinfeatmvvm.data.databases.QuoteDao
import com.todaysquare.kodeinfeatmvvm.data.databases.entites.Quote

class QuoteRepositoryImpl(private val quoteDao: QuoteDao) : QuoteRepository {

    override fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)

    }

    override fun getQuotes() = quoteDao.getQuotes()

}