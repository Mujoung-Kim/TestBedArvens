package com.todaysquare.dagger2_example.di

import com.todaysquare.dagger2_example.BaseApplication
import com.todaysquare.dagger2_example.MainActivity
import com.todaysquare.dagger2_example.di.modules.NetworkModule
import com.todaysquare.dagger2_example.di.modules.SharedModule
import com.todaysquare.dagger2_example.ui.fragments.HomeFragment

import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [SharedModule::class, NetworkModule::class])
interface SharedComponent {

    fun inject(application: BaseApplication)
    fun inject(application: MainActivity)
    fun inject(fragment: HomeFragment)

}