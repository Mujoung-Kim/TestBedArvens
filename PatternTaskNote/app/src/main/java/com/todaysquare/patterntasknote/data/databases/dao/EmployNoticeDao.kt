package com.todaysquare.patterntasknote.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.todaysquare.patterntasknote.data.databases.entities.EmployNotice

import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EmployNoticeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: List<EmployNotice>): Completable

    @Query("select * from employ order by 1 desc")
    fun getNotice(): Single<List<EmployNotice>>

}