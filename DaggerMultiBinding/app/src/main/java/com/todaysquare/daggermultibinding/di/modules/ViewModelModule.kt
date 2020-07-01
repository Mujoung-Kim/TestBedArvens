package com.todaysquare.daggermultibinding.di.modules

import androidx.lifecycle.ViewModel

import com.todaysquare.daggermultibinding.di.ViewModelKey
import com.todaysquare.daggermultibinding.ui.main.MainActivityViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

}