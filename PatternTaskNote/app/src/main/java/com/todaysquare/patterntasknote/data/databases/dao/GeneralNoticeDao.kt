package com.todaysquare.patterntasknote.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.todaysquare.patterntasknote.data.databases.entities.GeneralNotice

import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GeneralNoticeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: List<GeneralNotice>): Completable

    @Query("select * from general order by 1 desc")
    fun getNotice(): Single<List<GeneralNotice>>

}