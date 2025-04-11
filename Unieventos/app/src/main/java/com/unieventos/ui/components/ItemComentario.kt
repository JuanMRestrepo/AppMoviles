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
import androidx.compose.material.icons.rounded.Edit
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

/*
@Composable
fun ItemComentario(
    texto: String,
    nombreUsuario: String,
    fechaCreacion: Date,
    color: Color = MaterialTheme.colorScheme.primaryContainer
) {
    val formato = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val fechaFormateada = formato.format(fechaCreacion)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        color = color,
        shadowElevation = 3.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Icono de comentario"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Row {
                    Text(
                        text = nombreUsuario,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = fechaFormateada,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = texto,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}
 */