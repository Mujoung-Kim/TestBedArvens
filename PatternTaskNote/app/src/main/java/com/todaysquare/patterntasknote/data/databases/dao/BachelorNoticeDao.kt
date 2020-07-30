package com.todaysquare.patterntasknote.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BachelorNoticeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: List<BachelorNotice>): Completable

    @Query("select * from bachelor order by 1 desc")
    fun getNotice(): Single<List<BachelorNotice>>

}