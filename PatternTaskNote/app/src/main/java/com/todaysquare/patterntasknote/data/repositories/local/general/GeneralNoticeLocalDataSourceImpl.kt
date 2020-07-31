package com.todaysquare.patterntasknote.data.repositories.local.general

import com.todaysquare.patterntasknote.data.databases.dao.GeneralNoticeDao
import com.todaysquare.patterntasknote.data.databases.entities.GeneralNotice

import io.reactivex.Completable
import io.reactivex.Single

class GeneralNoticeLocalDataSourceImpl(private val generalNoticeDao: GeneralNoticeDao)
    : GeneralNoticeLocalDataSource {

    override fun insertNotice(notice: List<GeneralNotice>): Completable =
        generalNoticeDao.insertNotice(notice)

    override fun getNotice(): Single<List<GeneralNotice>> = generalNoticeDao.getNotice()

}