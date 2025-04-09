package com.unieventos.model

import java.time.LocalDateTime

class Notification(
    var id: String,
    var title: String,
    var message: String,
    var creationDate: LocalDateTime,
    var read: Boolean,
    var state: NotificationType,
    var reportId: String,
    var commentId: String,
    ) {
}