package com.todaysquare.patterntasknote.data.repositories.local.general

import com.todaysquare.patterntasknote.data.databases.entities.GeneralNotice

import io.reactivex.Completable
import io.reactivex.Single

interface GeneralNoticeLocalDataSource {

    fun insertNotice(notice: List<GeneralNotice>): Completable
    fun getNotice(): Single<List<GeneralNotice>>

}