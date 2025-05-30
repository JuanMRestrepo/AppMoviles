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
import androidx.compose.material.icons.filled.Check
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
import com.unieventos.model.ReportState
import com.unieventos.viewmodel.ReportsViewModel

@Composable
fun ReportVerifiedCardItem(
    report: Report,
    navigateToDetail: (String) -> Unit,
    reportsViewModel: ReportsViewModel
) {

    var showRejectDialog by rememberSaveable { mutableStateOf(false) }
    var showAcceptDialog by rememberSaveable { mutableStateOf(false) }

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
                text = report.state.toString(),
                fontSize = 14.sp
            )
            Text(
                text = report.title,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = report.description,
                fontSize = 14.sp
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(id = R.string.moreOptionsLbl),
                tint = Color(0xFF006400),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { showAcceptDialog = true }
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.closeLbl),
                tint = primaryColor,
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
                        reportsViewModel.solveReport(report.id, ReportState.REJECTED, rejectReason)
                    },
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
                ) {
                    Text(stringResource(id = R.string.closeLbl))
                }
            },
        )
    }

    if (showAcceptDialog) {
        AlertDialog(
            onDismissRequest = { showAcceptDialog = false },
            title = {
                Text(
                    text = stringResource(id = R.string.confirmLbl),
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(stringResource(id = R.string.sureConfirmLbl))
            },
            confirmButton = {
                Button(
                    onClick = {
                        reportsViewModel.updateReportState(report.id, ReportState.ACCEPTED)
                        showAcceptDialog = false
                    },
                ) {
                    Text(stringResource(id = R.string.confirmLbl))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showAcceptDialog = false }
                ) {
                    Text(stringResource(id = R.string.closeLbl))
                }
            },
        )
    }
}