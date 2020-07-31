package com.todaysquare.patterntasknote.data.repositories.local.bachelor

import com.todaysquare.patterntasknote.data.databases.dao.BachelorNoticeDao
import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice
import com.todaysquare.patterntasknote.data.repositories.local.bachelor.BachelorNoticeLocalDataSource

import io.reactivex.Completable
import io.reactivex.Single

class BachelorNoticeLocalLocalDataSourceImpl(private val bachelorNoticeDao: BachelorNoticeDao)
    :
    BachelorNoticeLocalDataSource {

    override fun insertNotice(notice: List<BachelorNotice>): Completable =
        bachelorNoticeDao.insertNotice(notice)

    override fun getNotice(): Single<List<BachelorNotice>> = bachelorNoticeDao.getNotice()

}