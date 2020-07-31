package com.todaysquare.patterntasknote.data.repositories

import com.todaysquare.patterntasknote.data.databases.entities.BusinessNotice

import io.reactivex.Flowable
import io.reactivex.Single

interface BusinessNoticeRepository {

    fun requestNotice(): Flowable<List<BusinessNotice>>
    fun requestMoreNotice(offset: Int): Single<List<BusinessNotice>>

}