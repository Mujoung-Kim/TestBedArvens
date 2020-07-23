package com.todaysquare.asynctaskdownload.data

import android.util.Log
import com.todaysquare.asynctaskdownload.utils.Constants.Param.Companion.TEST

class WorkerThread : Thread() {
    override fun run() {
        var i = 0

        while (i < 10) {
            i += 1

            Log.i(javaClass.simpleName + TEST, "$i")

        }
    }
}