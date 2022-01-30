package ru.nikitaartamonov.moviefinder.util.services

import android.app.Notification
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.nikitaartamonov.moviefinder.R

class FirebaseService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            handleDataMessage(remoteMessage.data.toMap())
        }
    }

    private fun handleDataMessage(data: Map<String, String>) {
        val title = data[PUSH_KEY_TITLE]
        val message = data[PUSH_KEY_MESSAGE]
        if (!title.isNullOrBlank() && !message.isNullOrBlank()) showNotification(title, message)
    }

    private fun showNotification(title: String, message: String) {
        val notification: Notification =
            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(message)
                .build()
        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val channel: NotificationChannelCompat = NotificationChannelCompat.Builder(
                CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH
            )
                .setName(getString(R.string.firebase_channel_name))
                .setDescription(getString(R.string.firebase_channel_description))
                .build()
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    override fun onNewToken(token: String) {
        Log.d("Token", "Refreshed token: $token")
    }
}

private const val PUSH_KEY_TITLE = "PUSH_KEY_TITLE"
private const val PUSH_KEY_MESSAGE = "PUSH_KEY_MESSAGE"
private const val CHANNEL_ID = "FIREBASE PUSH"
private const val NOTIFICATION_ID = 700