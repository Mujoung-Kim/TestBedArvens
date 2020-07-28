package com.todaysquare.asynctaskdownload.ui.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View

import com.todaysquare.asynctaskdownload.R
import com.todaysquare.asynctaskdownload.data.MyService
import com.todaysquare.asynctaskdownload.utils.Constants.Param.Companion.TEST
import org.jetbrains.anko.toast

class ServicesActivity : AppCompatActivity() {
    //  Bounded Service 부분
    var myService: MyService? = null
    var isService = false
    val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            isService = false

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.MyBinder

            myService = binder.getService()
            isService = true

            Log.d(javaClass.simpleName + TEST, "Connected.")

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

    }

    fun serviceStart(view: View) {
        val intent = Intent(this, MyService::class.java)

        intent.action = MyService.ACTION_START
        startService(intent)

    }

    fun serviceStop(view: View) {
        val intent = Intent(this, MyService::class.java)

        intent.action = MyService.ACTION_STOP
        stopService(intent)

    }

    fun serviceBind(view: View) {
        val intent = Intent(this, MyService::class.java)

        bindService(intent, connection, Context.BIND_AUTO_CREATE)

    }

    fun serviceUnbind(view: View) {
        if (isService) {
            unbindService(connection)
            isService = false

        }
    }

    fun callServiceFunction(view: View) {
        if (isService) {
            val message = myService?.serviceMessage()

            toast("message = $message")

        }
        else toast("Service not connected.")

    }
}