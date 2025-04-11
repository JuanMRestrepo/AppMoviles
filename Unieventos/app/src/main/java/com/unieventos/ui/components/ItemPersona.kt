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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*
@Composable
fun ItemPersona(
    nombreCompleto: String,
    correoElectronico: String,
    ciudad: String,
    esAdministrador: Boolean = false,
    fechaRegistro: Date,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val fechaFormateada = formatoFecha.format(fechaRegistro)

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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp),
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Imagen del usuario"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = nombreCompleto,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )

                    if (esAdministrador) {
                        Spacer(modifier = Modifier.width(8.dp))
                        // Custom admin badge instead of Chip
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFF7B1FA2))
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Admin",
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }

                Text(text = correoElectronico)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.height(16.dp),
                        tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = ciudad,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
                    )
                }

                Text(
                    text = "Miembro desde: $fechaFormateada",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            }
        }
    }
}
 */