package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.messaging.FirebaseMessaging
import com.unieventos.model.Notification
import com.unieventos.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class NotificationViewModel : ViewModel() {
    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications.asStateFlow()

    private val _loadingState = MutableStateFlow<RequestResult?>(null)
    val loadingState: StateFlow<RequestResult?> = _loadingState.asStateFlow()

    suspend fun getFCMToken(): String? {
        return try {
            FirebaseMessaging.getInstance().token.await()
        } catch (e: Exception) {
            _loadingState.value = RequestResult.Failure("Error obteniendo token: ${e.message}")
            null
        }
    }

    fun addNotification(notification: Notification) {
        _notifications.value = _notifications.value + notification
    }

    fun markAsRead(notificationId: String) {
        _notifications.value = _notifications.value.map { notification ->
            if (notification.id == notificationId) {
                Notification(
                    id = notification.id,
                    title = notification.title,
                    message = notification.message,
                    creationDate = notification.creationDate,
                    read = true,
                    state = notification.state,
                    reportId = notification.reportId,
                    commentId = notification.commentId
                )
            } else {
                notification
            }
        }
    }

    fun clearNotifications() {
        _notifications.value = emptyList()
    }
}