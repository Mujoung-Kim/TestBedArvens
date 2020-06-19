package com.todaysquare.restapiexample.data.databases

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.todaysquare.restapiexample.R

import org.json.JSONObject

@SuppressLint("Registered")
class FirebaseInstanceIDService : FirebaseMessagingService(), LifecycleObserver {
    companion object {
        private const val TAG = "Firebase"

    }

    private var isAppInForeground: Boolean = false

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        ProcessLifecycleOwner.get().lifecycle.removeObserver(this)

    }

    fun onForegroundStart() {
        isAppInForeground = true

    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e(TAG, "Token : $p0")

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if (!isAppInForeground) {
            if (remoteMessage.data.isNotEmpty()) {
                sendNotification(remoteMessage)
                Log.d(TAG, "Title : ${remoteMessage.data["title"]}")
                Log.d(TAG, "Message : ${remoteMessage.data["message"]}")

            }
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val title = remoteMessage.data["title"]
        val messageData = JSONObject(remoteMessage.data["message"])
        val textData = messageData.getJSONObject("data").getString("text")
        val notificationID = messageData.getInt("id")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelID = "SimpleChatChannelID"
            val channelName = "SimpleChat Channel"
            val notificationChannel =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelMessage =
                NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)

            channelMessage.description = "Notification channel from SimpleChat"
            channelMessage.enableLights(true)
            channelMessage.enableVibration(true)
            channelMessage.setShowBadge(false)
            notificationChannel.createNotificationChannel(channelMessage)

            val notificationBuilder = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(textData)
                .setChannelId(channelID)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(notificationID, notificationBuilder.build())

        } else {
            val notificationBuilder = NotificationCompat.Builder(this, "")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(textData)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(notificationID, notificationBuilder.build())

        }
    }
}