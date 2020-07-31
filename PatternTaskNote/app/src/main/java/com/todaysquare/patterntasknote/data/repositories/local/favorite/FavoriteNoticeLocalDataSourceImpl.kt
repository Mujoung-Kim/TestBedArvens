package com.todaysquare.patterntasknote.data.repositories.local.favorite

import com.todaysquare.patterntasknote.data.databases.dao.FavoriteNoticeDao
import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice
import com.todaysquare.patterntasknote.data.repositories.local.favorite.FavoriteNoticeLocalDataSource

import io.reactivex.Completable
import io.reactivex.Single

class FavoriteNoticeLocalDataSourceImpl(private val favoriteNoticeDao: FavoriteNoticeDao)
    :
    FavoriteNoticeLocalDataSource {

    override fun insertNotice(notice: FavoriteNotice): Completable = favoriteNoticeDao.insertNotice(notice)

    override fun getNotice(): Single<List<FavoriteNotice>> = favoriteNoticeDao.getNotice()

    override fun deleteNotice(notice: FavoriteNotice): Completable = favoriteNoticeDao.deleteNotice(notice)

}