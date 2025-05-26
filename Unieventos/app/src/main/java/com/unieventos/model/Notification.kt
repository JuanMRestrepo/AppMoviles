package com.unieventos.model

import java.util.Date

class Notification(
    var id: String,
    var title: String,
    var message: String,
    var creationDate: Date,
    var read: Boolean,
    var state: NotificationType,
    var reportId: String,
    var commentId: String,
    ) {
}