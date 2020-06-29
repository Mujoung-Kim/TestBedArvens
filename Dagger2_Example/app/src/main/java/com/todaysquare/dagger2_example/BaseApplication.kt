package com.todaysquare.dagger2_example

import android.app.Application

import com.todaysquare.dagger2_example.di.DaggerSharedComponent
import com.todaysquare.dagger2_example.di.SharedComponent
import com.todaysquare.dagger2_example.di.modules.SharedModule

class BaseApplication : Application() {
    lateinit var component: SharedComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerSharedComponent.builder().sharedModule(
            SharedModule(
                this
            )
        ).build()

    }

    fun getSharedComponent(): SharedComponent = component

}