package com.todaysquare.asynctaskdownload.data

import android.util.Log

import com.todaysquare.asynctaskdownload.utils.Constants

class WorkerRunnable : Runnable {
    override fun run() {
        var i = 10

         while (i < 20) {
             i += 1
             Log.i(javaClass.simpleName + Constants.Param.TEST, "$i")

         }
    }
}