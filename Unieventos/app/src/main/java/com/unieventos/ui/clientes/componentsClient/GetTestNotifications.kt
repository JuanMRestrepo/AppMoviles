package com.unieventos.ui.clientes.componentsClient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.unieventos.model.Notification
import com.unieventos.model.NotificationType
import java.time.LocalDateTime
import java.util.Date

@Composable
fun GetTestNotifications(): List<Notification> {
    return remember {
        listOf(
            Notification(
                id = "1",
                title = "Nuevo reporte",
                message = "Se ha creado un nuevo reporte en tu área",
                creationDate = Date(),
                read = false,
                state = NotificationType.NEW_REPORT,
                reportId = "101",
                commentId = ""
            ),
            Notification(
                id = "2",
                title = "Nuevo comentario",
                message = "Tienes un nuevo comentario en tu reporte",
                creationDate = Date(),
                read = true,
                state = NotificationType.NEW_COMMENT,
                reportId = "102",
                commentId = "201"
            ),
            Notification(
                id = "3",
                title = "Reporte verificado",
                message = "Tu reporte ha sido verificado",
                creationDate = Date(),
                read = false,
                state = NotificationType.REPORT_VERIFIED,
                reportId = "103",
                commentId = ""
            ),
            Notification(
                id = "4",
                title = "Reporte rechazado",
                message = "Tu reporte ha sido rechazado",
                creationDate = Date(),
                read = true,
                state = NotificationType.REPORT_REJECTED,
                reportId = "104",
                commentId = ""
            ),
            Notification(
                id = "5",
                title = "Reporte resuelto",
                message = "Tu reporte ha sido marcado como resuelto",
                creationDate = Date(),
                read = false,
                state = NotificationType.REPORT_RESOLVED,
                reportId = "105",
                commentId = ""
            )
        )
    }
}
