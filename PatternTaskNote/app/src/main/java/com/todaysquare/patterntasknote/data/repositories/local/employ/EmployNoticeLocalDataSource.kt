package com.todaysquare.patterntasknote.data.repositories.local.employ

import com.todaysquare.patterntasknote.data.databases.entities.EmployNotice

import io.reactivex.Completable
import io.reactivex.Single

interface EmployNoticeLocalDataSource {

    fun insertNotice(notice: List<EmployNotice>): Completable
    fun getNotice(): Single<List<EmployNotice>>

}