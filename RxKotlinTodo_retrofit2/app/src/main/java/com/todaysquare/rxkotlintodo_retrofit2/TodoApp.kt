package com.todaysquare.rxkotlintodo_retrofit2

import android.app.Application

class TodoApp : Application() {
    companion object {
        var instance: TodoApp? = null

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}