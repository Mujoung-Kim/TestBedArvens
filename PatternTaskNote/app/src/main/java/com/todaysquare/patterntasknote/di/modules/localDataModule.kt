package com.todaysquare.patterntasknote.di.modules

import androidx.room.Room
import com.todaysquare.patterntasknote.data.databases.NoticeDatabase

import com.todaysquare.patterntasknote.data.databases.dao.BachelorNoticeDao
import com.todaysquare.patterntasknote.data.databases.dao.BusinessNoticeDao
import com.todaysquare.patterntasknote.data.databases.dao.EmployNoticeDao
import com.todaysquare.patterntasknote.data.databases.dao.FavoriteNoticeDao
import com.todaysquare.patterntasknote.data.repositories.local.bachelor.BachelorNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.local.bachelor.BachelorNoticeLocalLocalDataSourceImpl
import com.todaysquare.patterntasknote.data.repositories.local.business.BusinessNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.local.business.BusinessNoticeLocalDataSourceImpl
import com.todaysquare.patterntasknote.data.repositories.local.employ.EmployNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.local.employ.EmployNoticeLocalDataSourceImpl
import com.todaysquare.patterntasknote.data.repositories.local.favorite.FavoriteNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.local.favorite.FavoriteNoticeLocalDataSourceImpl
import com.todaysquare.patterntasknote.data.repositories.local.general.GeneralNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.local.general.GeneralNoticeLocalDataSourceImpl

import org.koin.dsl.module

val localDataModule = module {
    single<BachelorNoticeLocalDataSource> { BachelorNoticeLocalLocalDataSourceImpl(get()) }
    single<EmployNoticeLocalDataSource> { EmployNoticeLocalDataSourceImpl(get()) }
    single<BusinessNoticeLocalDataSource> { BusinessNoticeLocalDataSourceImpl(get()) }
    single<GeneralNoticeLocalDataSource> { GeneralNoticeLocalDataSourceImpl(get()) }
    single<FavoriteNoticeLocalDataSource> { FavoriteNoticeLocalDataSourceImpl(get()) }

    single { get<NoticeDatabase>().bachelorNoticeDao() }
    single { get<NoticeDatabase>().employNoticeDao() }
    single { get<NoticeDatabase>().businessNoticeDao() }
    single { get<NoticeDatabase>().generalNoticeDao() }
    single { get<NoticeDatabase>().favoriteNoticeDao() }

    single { Room.databaseBuilder(get(), NoticeDatabase::class.java, "Notice.db").build() }

}