package com.todaysquare.patterntasknote.data.repositories.remote.general

import com.todaysquare.patterntasknote.data.databases.entities.GeneralNotice

import io.reactivex.Single

interface GeneralNoticeRemoteDataSource {

    fun requestNotice(): Single<List<GeneralNotice>>
    fun requestMoreNotice(offset: Int): Single<List<GeneralNotice>>

}