package com.todaysquare.patterntasknote.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.todaysquare.patterntasknote.data.databases.dao.*

import com.todaysquare.patterntasknote.data.databases.entities.*

@Database(entities = [BachelorNotice::class, BusinessNotice::class, EmployNotice::class,
    GeneralNotice::class, FavoriteNotice::class], version = 1, exportSchema = false)
abstract class NoticeDatabase : RoomDatabase() {
    abstract fun bachelorNoticeDao(): BachelorNoticeDao
    abstract fun businessNoticeDao(): BusinessNoticeDao
    abstract fun employNoticeDao(): EmployNoticeDao
    abstract fun favoriteNoticeDao(): FavoriteNoticeDao
    abstract fun generalNoticeDao(): GeneralNoticeDao

}