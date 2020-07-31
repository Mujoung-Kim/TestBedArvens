package com.todaysquare.patterntasknote.data.repositories

import com.todaysquare.patterntasknote.data.databases.entities.GeneralNotice

import io.reactivex.Flowable
import io.reactivex.Single

interface GeneralNoticeRepository {

    fun requestNotice(): Flowable<List<GeneralNotice>>
    fun requestMoreNotice(offset: Int): Single<List<GeneralNotice>>

}