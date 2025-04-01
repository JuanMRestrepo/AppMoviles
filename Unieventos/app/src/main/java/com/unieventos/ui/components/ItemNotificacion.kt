package com.unieventos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class TipoNotificacion {
    NUEVO_REPORTE,
    NUEVO_COMENTARIO,
    REPORTE_VERIFICADO,
    REPORTE_RECHAZADO,
    REPORTE_RESUELTO
}

@Composable
fun ItemNotificacion(
    titulo: String,
    mensaje: String,
    fechaCreacion: Date,
    leida: Boolean = false,
    tipo: TipoNotificacion,
    color: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    val formato = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val fechaFormateada = formato.format(fechaCreacion)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        color = if (leida) color.copy(alpha = 0.7f) else color,
        shadowElevation = if (leida) 1.dp else 3.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(getNotificationTypeColor(tipo)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(30.dp),
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icono de notificación"
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = titulo,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    if (!leida) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = mensaje,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = fechaFormateada,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun getNotificationTypeColor(tipo: TipoNotificacion): Color {
    return when (tipo) {
        TipoNotificacion.NUEVO_REPORTE -> Color(0xFF4CAF50)
        TipoNotificacion.NUEVO_COMENTARIO -> Color(0xFF2196F3)
        TipoNotificacion.REPORTE_VERIFICADO -> Color(0xFF9C27B0)
        TipoNotificacion.REPORTE_RECHAZADO -> Color(0xFFF44336)
        TipoNotificacion.REPORTE_RESUELTO -> Color(0xFF009688)
    }
}