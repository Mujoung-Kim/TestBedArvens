package com.todaysquare.patterntasknote.data.repositories

import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice

import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteNoticeRepository {

    fun requestNotice(): Single<List<FavoriteNotice>>
    fun insertNotice(notice: FavoriteNotice): Completable
    fun deleteNotice(notice: FavoriteNotice): Completable

}