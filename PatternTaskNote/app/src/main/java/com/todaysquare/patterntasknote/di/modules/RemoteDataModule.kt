package com.todaysquare.patterntasknote.di.modules

import com.todaysquare.patterntasknote.data.repositories.remote.bachelor.BachelorNoticeRemoteDataSource
import com.todaysquare.patterntasknote.data.repositories.remote.bachelor.BachelorNoticeRemoteDataSourceImpl
import com.todaysquare.patterntasknote.data.repositories.remote.business.BusinessNoticeRemoteDataSource
import com.todaysquare.patterntasknote.data.repositories.remote.business.BusinessNoticeRemoteDataSourceImpl
import com.todaysquare.patterntasknote.data.repositories.remote.employ.EmployNoticeRemoteDataSource
import com.todaysquare.patterntasknote.data.repositories.remote.employ.EmployNoticeRemoteDataSourceImpl
import com.todaysquare.patterntasknote.data.repositories.remote.general.GeneralNoticeRemoteDataSource
import com.todaysquare.patterntasknote.data.repositories.remote.general.GeneralNoticeRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataModule = module {
    single<BachelorNoticeRemoteDataSource> { BachelorNoticeRemoteDataSourceImpl() }
    single<EmployNoticeRemoteDataSource> { EmployNoticeRemoteDataSourceImpl() }
    single<BusinessNoticeRemoteDataSource> { BusinessNoticeRemoteDataSourceImpl() }
    single<GeneralNoticeRemoteDataSource> { GeneralNoticeRemoteDataSourceImpl() }

}