package com.todaysquare.patterntasknote.data.repositories.remote.employ

import com.todaysquare.patterntasknote.data.databases.entities.EmployNotice

import io.reactivex.Single

interface EmployNoticeRemoteDataSource {

    fun requestNotice(): Single<List<EmployNotice>>
    fun requestMoreNotice(offset: Int): Single<List<EmployNotice>>

}