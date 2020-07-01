package com.todaysquare.daggermultibinding

import com.todaysquare.daggermultibinding.di.components.DaggerAppComponent
import com.todaysquare.daggermultibinding.utils.Constants.Url.Companion.BASE_URL

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DaggerMVVMMultiBindingApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .application(this)
            .baseUrl(BASE_URL)
            .build()

}