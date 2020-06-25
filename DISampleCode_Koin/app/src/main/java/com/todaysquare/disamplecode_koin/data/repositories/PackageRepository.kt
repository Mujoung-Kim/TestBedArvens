package com.todaysquare.disamplecode_koin.data.repositories

import android.content.Context

class PackageRepository(context: Context) {
    val packageName: String

    init {
        packageName = context.packageName
        
    }
}