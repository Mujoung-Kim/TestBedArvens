package com.todaysquare.patterntasknote.di.modules

import com.todaysquare.patterntasknote.data.repositories.*
import com.todaysquare.patterntasknote.data.repositories.impl.*
import org.koin.dsl.module

val repositoryModule = module {
    single<BachelorNoticeRepository> { BachelorNoticeRepositoryImpl(get(), get()) }
    single<EmployNoticeRepository> { EmployNoticeRepositoryImpl(get(), get()) }
    single<BusinessNoticeRepository> { BusinessNoticeRepositoryImpl(get(), get()) }
    single<GeneralNoticeRepository> { GeneralNoticeRepositoryImpl(get(), get()) }
    single<FavoriteNoticeRepository> { FavoriteNoticeRepositoryImpl(get()) }

}