package com.todaysquare.patterntasknote.data.repositories.local.business

import com.todaysquare.patterntasknote.data.databases.entities.BusinessNotice

import io.reactivex.Completable
import io.reactivex.Single

interface BusinessNoticeLocalDataSource {

    fun insertNotice(notice: List<BusinessNotice>): Completable
    fun getNotice(): Single<List<BusinessNotice>>

}