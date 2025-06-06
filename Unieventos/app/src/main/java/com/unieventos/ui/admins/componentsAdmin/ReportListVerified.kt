package com.unieventos.ui.admins.componentsAdmin

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
import com.unieventos.viewmodel.ReportsViewModel

@Composable
fun ReportsListVerified(
    reports: List<Report>,
    navigateToDetail: (String) -> Unit,
    reportsViewModel: ReportsViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 5.dp)
    ) {
        items(reports) { report ->
            ReportVerifiedCardItem (
                report = report,
                navigateToDetail = navigateToDetail,
                reportsViewModel = reportsViewModel
            )
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 1.dp
            )
        }
    }
}