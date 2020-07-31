package com.todaysquare.patterntasknote.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.todaysquare.patterntasknote.data.databases.entities.BusinessNotice

import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BusinessNoticeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: List<BusinessNotice>): Completable

    @Query("select * from business order by 1 desc")
    fun getNotice(): Single<List<BusinessNotice>>

}