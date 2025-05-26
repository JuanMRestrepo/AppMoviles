package com.unieventos.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.unieventos.R
import com.unieventos.model.Notification
import com.unieventos.model.NotificationType
import java.util.Date

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val notificationData = message.data
        val notification = createNotificationFromData(notificationData)

        showSystemNotification(notification)
    }

    private fun createNotificationFromData(data: Map<String, String>): Notification {
        return Notification(
            id = data["id"] ?: System.currentTimeMillis().toString(),
            title = data["title"] ?: "",
            message = data["message"] ?: "",
            creationDate = Date(),
            read = false,
            state = NotificationType.valueOf(data["type"] ?: "NEW_REPORT"),
            reportId = data["reportId"] ?: "",
            commentId = data["commentId"] ?: ""
        )
    }

    private fun showSystemNotification(notification: Notification) {
        val channelId = "unieventos_channel"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(channelId, notificationManager)

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.red_marker)
            .setContentTitle(notification.title)
            .setContentText(notification.message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(notification.id.hashCode(), builder.build())
    }

    private fun createNotificationChannel(channelId: String, manager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notificaciones UniEventos",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "notificaciones de la aplicación UniEventos"
            }
            manager.createNotificationChannel(channel)
        }
    }
}