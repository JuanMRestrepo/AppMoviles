package com.unieventos.ui.clientes.componentsClient

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.unieventos.R
import com.unieventos.model.Report
import com.unieventos.model.ReportState

@Composable
fun ReportCardItemStatus(
    report: Report,
    navigateToDetail: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToDetail(report.id) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Contenido principal
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = report.images[0],
                contentDescription = stringResource(id = R.string.imageReport),
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = report.title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = report.description,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(24.dp)
            ) {
                if (report.state == ReportState.ACCEPTED) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Accepted",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(28.dp)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .border(
                                2.dp,
                                Color(0xFF9E9E9E),
                                RoundedCornerShape(4.dp)
                            )
                    )
                }
            }

            Text(
                text = when (report.state) {
                    ReportState.ACCEPTED -> "Verified"
                    ReportState.PENDING -> "Earring"
                    else -> ""
                },
                color = when (report.state) {
                    ReportState.ACCEPTED -> Color(0xFF4CAF50)
                    ReportState.PENDING -> Color(0xFF9E9E9E)
                    else -> Color.Transparent
                },
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }


    }
}