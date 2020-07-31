package com.todaysquare.patterntasknote.data.repositories.local.bachelor

import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice

import io.reactivex.Completable
import io.reactivex.Single

interface BachelorNoticeLocalDataSource {

    fun insertNotice(notice: List<BachelorNotice>): Completable
    fun getNotice(): Single<List<BachelorNotice>>

}