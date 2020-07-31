package com.todaysquare.patterntasknote.data.databases.dao

import androidx.room.*

import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice

import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteNoticeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: FavoriteNotice): Completable

    @Query("select * from favorite order by 1 desc")
    fun getNotice(): Single<List<FavoriteNotice>>

    @Delete
    fun deleteNotice(notice: FavoriteNotice): Completable

}