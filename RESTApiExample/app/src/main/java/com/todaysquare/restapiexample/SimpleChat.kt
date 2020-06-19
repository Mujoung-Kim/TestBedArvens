package com.todaysquare.restapiexample

import android.app.Application
import android.util.Log

import com.cometchat.pro.core.AppSettings
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class SimpleChat : Application() {
    companion object {
        private const val TAG = "SimpleChatApp"

    }

    override fun onCreate() {
        super.onCreate()

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceID failed", task.exception)
                    return@OnCompleteListener

                }

                val token = task.result?.token

                Log.d(TAG, token)

            })

        val appSettings = AppSettings.AppSettingsBuilder()
            .subscribePresenceForFriends()
            .setRegion(getString(R.string.region))
            .build()

        CometChat.init(this, getString(R.string.appID), appSettings,
            object : CometChat.CallbackListener<String>() {
                override fun onSuccess(message: String?) {
                    Log.d(TAG, "CometChat initialization completed: $message")

                }

                override fun onError(error: CometChatException) {
                    Log.d(TAG, "CometChat initialization failed : ${error.message}")

                }
            })
    }
}