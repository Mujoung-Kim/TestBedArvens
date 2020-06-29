package com.todaysquare.dagger2_example.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.todaysquare.dagger2_example.di.ViewModuleKey
import com.todaysquare.dagger2_example.ui.viewmodels.HomeViewModel
import com.todaysquare.dagger2_example.ui.viewmodels.ViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModuleKey(HomeViewModel::class)
    abstract fun bindViewModule(homeViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}