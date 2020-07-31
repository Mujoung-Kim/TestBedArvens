package com.todaysquare.patterntasknote.data.repositories.local.employ

import com.todaysquare.patterntasknote.data.databases.dao.EmployNoticeDao
import com.todaysquare.patterntasknote.data.databases.entities.EmployNotice
import com.todaysquare.patterntasknote.data.repositories.local.employ.EmployNoticeLocalDataSource

import io.reactivex.Completable
import io.reactivex.Single

class EmployNoticeLocalDataSourceImpl(private val noticeDao: EmployNoticeDao)
    :
    EmployNoticeLocalDataSource {

    override fun insertNotice(notice: List<EmployNotice>): Completable = noticeDao.insertNotice(notice)

    override fun getNotice(): Single<List<EmployNotice>> = noticeDao.getNotice()

}