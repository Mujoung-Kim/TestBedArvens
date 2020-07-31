package com.todaysquare.patterntasknote.data.repositories.impl

import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice
import com.todaysquare.patterntasknote.data.repositories.FavoriteNoticeRepository
import com.todaysquare.patterntasknote.data.repositories.local.favorite.FavoriteNoticeLocalDataSource

import io.reactivex.Completable
import io.reactivex.Single

class FavoriteNoticeRepositoryImpl(private val localDataSource: FavoriteNoticeLocalDataSource)
    : FavoriteNoticeRepository {

    override fun requestNotice(): Single<List<FavoriteNotice>> = localDataSource.getNotice()

    override fun insertNotice(notice: FavoriteNotice): Completable = localDataSource.insertNotice(notice)

    override fun deleteNotice(notice: FavoriteNotice): Completable = localDataSource.deleteNotice(notice)

}