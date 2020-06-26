package com.todaysquare.kodeinfeatmvvm.data.databases

class AppDatabaseFakeImpl : AppDatabase { override val quoteDao: QuoteDao = QuoteDaoFakeImpl() }