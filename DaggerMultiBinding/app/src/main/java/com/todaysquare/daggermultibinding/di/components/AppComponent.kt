package com.todaysquare.daggermultibinding.di.components

import android.app.Application

import com.todaysquare.daggermultibinding.DaggerMVVMMultiBindingApplication
import com.todaysquare.daggermultibinding.di.modules.ActivityBuilderModule
import com.todaysquare.daggermultibinding.di.modules.ApiModule
import com.todaysquare.daggermultibinding.di.modules.AppModule
import com.todaysquare.daggermultibinding.di.modules.ViewModelFactoryModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class,
    AppModule::class, ApiModule::class, ViewModelFactoryModule::class])
interface AppComponent : AndroidInjector<DaggerMVVMMultiBindingApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun baseUrl(@Named("baseUrl") baseUrl: String): Builder

        fun build(): AppComponent

    }
}