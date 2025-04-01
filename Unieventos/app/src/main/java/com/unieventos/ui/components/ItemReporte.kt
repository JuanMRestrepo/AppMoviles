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
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemReporte(
    titulo: String,
    categoria: String,
    ciudad: String,
    esVerificado: Boolean = false,
    color: Color = MaterialTheme.colorScheme.primary
) {
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
                imageVector = Icons.Rounded.Warning,
                contentDescription = "Icono de reporte"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = titulo, fontSize = 20.sp)
                    if (esVerificado) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "✓",
                            color = Color.Green,
                            fontSize = 20.sp
                        )
                    }
                }
                Text(text = "Categoría: $categoria")
                Text(text = "Ciudad: $ciudad")
            }
        }
    }
}