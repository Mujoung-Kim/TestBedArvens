package com.todaysquare.daggermultibinding.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.todaysquare.daggermultibinding.ui.ViewModelFactory

import dagger.Module
import dagger.Provides

import javax.inject.Provider
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>)
            : ViewModelProvider.Factory = ViewModelFactory(providerMap)

}