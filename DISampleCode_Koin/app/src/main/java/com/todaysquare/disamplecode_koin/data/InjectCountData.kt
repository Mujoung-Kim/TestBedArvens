package com.todaysquare.disamplecode_koin.data

import android.util.Log

class InjectCountData {
    companion object {
        var injectCount = 0

    }
    private val TAG = this.javaClass.simpleName

    init {
        injectCount++

    }

    fun printCount() { Log.v(TAG, "injectCount >>> $injectCount")}

}