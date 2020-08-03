package com.todaysquare.patterntasknote.di.modules

import com.todaysquare.patterntasknote.ui.bachelor.BachelorNoticeViewModel
import com.todaysquare.patterntasknote.ui.business.BusinessNoticeViewModel
import com.todaysquare.patterntasknote.ui.employ.EmployNoticeViewModel
import com.todaysquare.patterntasknote.ui.favorite.FavoriteViewModel
import com.todaysquare.patterntasknote.ui.general.GeneralNoticeViewModel
import com.todaysquare.patterntasknote.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BachelorNoticeViewModel(get()) }
    viewModel { EmployNoticeViewModel(get()) }
    viewModel { BusinessNoticeViewModel(get()) }
    viewModel { GeneralNoticeViewModel(get()) }
    viewModel { LoginViewModel() }
    viewModel { FavoriteViewModel(get()) }

}