package com.unieventos.ui.admins.componentsAdmin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun AllReportsItem(
    report: Report,
    navigateToDetail: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToDetail(report.id) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
                text = report.state.toString(),
                color = Color.Gray,
                fontSize = 14.sp
            )
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
    }
}