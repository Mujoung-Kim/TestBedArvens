package com.todaysquare.asynctaskdownload.data

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.view.View
import com.todaysquare.asynctaskdownload.utils.Constants.Param.Companion.TEST

class MyService : Service() {
    companion object {
        //  Service command
        const val ACTION_START = "com.todaysquare.asynctaskdownload.START"
        const val ACTION_RUN = "com.todaysquare.asynctaskdownload.RUN"
        const val ACTION_STOP = "com.todaysquare.asynctaskdownload.STOP"

    }
    //  bounded Service
    val binder = MyBinder()

    override fun onBind(intent: Intent?): IBinder? = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action

        Log.d(javaClass.simpleName + TEST, "action = $action")

        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        Log.d(javaClass.simpleName + TEST, "Service terminated.")
        super.onDestroy()

    }

    inner class MyBinder : Binder() {
        fun getService(): MyService = this@MyService

    }

    fun serviceMessage(): String = "Hello Activity! I am service."

}