package com.todaysquare.patterntasknote.di.modules

import androidx.room.Room
import com.todaysquare.patterntasknote.data.repositories.local.BachelorNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.local.BachelorNoticeLocalLocalDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val localDataModule: Module = module {
    single<BachelorNoticeLocalDataSource> { BachelorNoticeLocalLocalDataSourceImpl(get()) }

    single<> {
        Room.databaseBuilder(get(), )
    }
}