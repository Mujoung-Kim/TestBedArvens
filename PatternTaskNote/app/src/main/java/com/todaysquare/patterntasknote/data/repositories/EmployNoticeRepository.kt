package com.todaysquare.patterntasknote.data.repositories

import com.todaysquare.patterntasknote.data.databases.entities.EmployNotice

import io.reactivex.Flowable
import io.reactivex.Single

interface EmployNoticeRepository {

    fun requestNotice(): Flowable<List<EmployNotice>>
    fun requestMoreNotice(offset: Int): Single<List<EmployNotice>>

}