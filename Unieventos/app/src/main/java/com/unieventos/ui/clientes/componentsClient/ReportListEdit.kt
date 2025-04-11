package com.unieventos.ui.clientes.componentsClient

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.unieventos.model.Report

@Composable
fun ReportListEdit(
    reports: List<Report>,
    navigateToDetail: (String) -> Unit,
    navigateToEdit: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 5.dp)
    ) {
        items(reports) { report ->
            ReportCardEditItem(
                report = report,
                navigateToDetail = navigateToDetail,
                navigateToEdit = navigateToEdit)
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 1.dp
            )
        }
    }
}