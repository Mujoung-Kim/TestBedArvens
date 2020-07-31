package com.todaysquare.patterntasknote.data.repositories.local.favorite

import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice

import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteNoticeLocalDataSource {

    fun insertNotice(notice: FavoriteNotice): Completable
    fun getNotice(): Single<List<FavoriteNotice>>
    fun deleteNotice(notice: FavoriteNotice): Completable

}