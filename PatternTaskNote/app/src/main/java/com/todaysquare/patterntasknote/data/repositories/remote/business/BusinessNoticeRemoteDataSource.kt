package com.todaysquare.patterntasknote.data.repositories.remote.business

import com.todaysquare.patterntasknote.data.databases.entities.BusinessNotice

import io.reactivex.Single

interface BusinessNoticeRemoteDataSource {

    fun requestNotice(): Single<List<BusinessNotice>>
    fun requestMoreNotice(offset: Int): Single<List<BusinessNotice>>

}