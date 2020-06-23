package com.todaysquare.iamportsamplecode.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import com.todaysquare.iamportsamplecode.data.network.Api

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val smsAuthApi by lazy { Api.createForImport(application) }
}