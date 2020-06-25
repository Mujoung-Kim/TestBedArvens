package com.todaysquare.disamplecode_koin.utils

import android.util.Log

import com.todaysquare.disamplecode_koin.data.repositories.PackageRepository

class PrintService(val packageRepository: PackageRepository) {
    fun printHello() { Log.v("PrintService", ">>> ${packageRepository.packageName}") }

}