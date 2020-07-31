package com.todaysquare.patterntasknote.data.repositories.local.business

import com.todaysquare.patterntasknote.data.databases.dao.BusinessNoticeDao
import com.todaysquare.patterntasknote.data.databases.entities.BusinessNotice

import io.reactivex.Completable
import io.reactivex.Single

class BusinessNoticeLocalDataSourceImpl(private val noticeDao: BusinessNoticeDao)
    : BusinessNoticeLocalDataSource {

    override fun insertNotice(notice: List<BusinessNotice>): Completable = noticeDao.insertNotice(notice)

    override fun getNotice(): Single<List<BusinessNotice>> = noticeDao.getNotice()

}