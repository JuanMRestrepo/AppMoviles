package com.unieventos.ui.admins.componentsAdmin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Beenhere
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
fun ReportVerifiedCardItem(
    report: Report,
    navigateToDetail: (String) -> Unit
) {

    var showRejectDialog by rememberSaveable { mutableStateOf(false) }
    var rejectReason by rememberSaveable { mutableStateOf("") }
    val primaryColor = Color(0xFFFF4A3D)

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
                text = report.id,
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

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Beenhere,
                contentDescription = stringResource(id = R.string.moreOptionsLbl),
                tint = Color(0xFF006400),
                modifier = Modifier.size(24.dp)
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.closeLbl),
                tint = Color.Red,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { showRejectDialog = true }
            )
        }
    }

    if (showRejectDialog) {
        AlertDialog(
            onDismissRequest = { showRejectDialog = false },
            title = {
                Text(
                    stringResource(id = R.string.reasonLblMessage),
                    color = primaryColor,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                OutlinedTextField(
                    value = rejectReason,
                    onValueChange = { rejectReason = it },
                    label = { Text(stringResource(id = R.string.reasonLbl)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    maxLines = 5
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showRejectDialog = false
                        // rejectReport(report.id, rejectReason)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor
                    )
                ) {
                    Text(stringResource(id = R.string.confirmLbl))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showRejectDialog = false
                        rejectReason = ""
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = primaryColor
                    )
                ) {
                    Text(stringResource(id = R.string.closeLbl))
                }
            },
            containerColor = Color.White
        )
    }
}