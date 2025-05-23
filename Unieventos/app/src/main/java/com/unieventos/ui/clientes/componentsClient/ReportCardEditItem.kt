package com.unieventos.ui.clientes.componentsClient

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import com.unieventos.viewmodel.ReportsViewModel

@Composable
fun ReportCardEditItem(
    report: Report,
    navigateToDetail: (String) -> Unit,
    navigateToEdit: (String) -> Unit,
    reportsViewModel: ReportsViewModel
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val primaryColor = Color(0xFFFF4A3D)
    var showDeleteDialog by rememberSaveable { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(stringResource(R.string.deleteReportLbl)) },
            text = { Text(stringResource(R.string.deleteReportSureLbl)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        reportsViewModel.deleteReport(report.id)
                        showDeleteDialog = false
                    }
                ) {
                    Text(stringResource(R.string.deleteLbl), color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(stringResource(R.string.closeLbl))
                }
            }
        )
    }

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
                text = report.title,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = report.description,
                fontSize = 14.sp
            )
        }

        Box {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(id = R.string.moreOptionsLbl),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(150.dp)
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = R.string.editingLbl)
                        )
                    },
                    onClick = {
                        expanded = false
                        navigateToEdit(report.id)
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = R.string.deleteLbl)
                        )
                    },
                    onClick = {
                        expanded = false
                        showDeleteDialog = true
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = R.string.closeLbl)
                        )
                    },
                    onClick = {
                        expanded = false
                    }
                )
            }
        }
    }
}