package com.unieventos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ItemRechazarReporte(
    motivo: String,
    fechaRechazo: Date,
    fechaLimiteModificacion: Date,
    nombreModerador: String,
    color: Color = MaterialTheme.colorScheme.errorContainer
) {
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val fechaRechazoFormateada = formatoFecha.format(fechaRechazo)
    val fechaLimiteFormateada = formatoFecha.format(fechaLimiteModificacion)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        color = color,
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                imageVector = Icons.Rounded.Clear,
                contentDescription = "Icono de reporte rechazado"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Reporte Rechazado",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Motivo del rechazo:",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.8f)
                )

                Text(
                    text = motivo,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Text(
                        text = "Fecha de rechazo: ",
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = fechaRechazoFormateada,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Row {
                    Text(
                        text = "Fecha límite para modificar: ",
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = fechaLimiteFormateada,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Rechazado por: $nombreModerador",
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.7f)
                )
            }
        }
    }
}