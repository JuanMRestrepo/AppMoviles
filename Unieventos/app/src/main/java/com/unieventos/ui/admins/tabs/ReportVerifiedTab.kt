package com.unieventos.ui.admins.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.model.ReportState
import com.unieventos.ui.admins.componentsAdmin.ReportsListVerified
import com.unieventos.ui.clientes.componentsClient.ModifiedSearchBar
import com.unieventos.ui.components.AlertMessage
import com.unieventos.ui.components.AlertType
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.RequestResult
import kotlinx.coroutines.delay

@Composable
fun ReportVerifiedTab(
    navigateToDetail: (String) -> Unit
) {

    val reportsViewModel = LocalMainViewModel.current.reportsViewModel
    val reports by reportsViewModel.reports.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val result by reportsViewModel.reportResult.collectAsState()

    val filteredReports = remember(reports, searchQuery) {
        reports.filter { report ->
            report.state == ReportState.PENDING && (
                    searchQuery.isEmpty() ||
                            report.title.contains(searchQuery, ignoreCase = true) ||
                            report.description.contains(searchQuery, ignoreCase = true)
                    )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.reportsToVerifiedLbl),
                color = Color(0xFFFF4A3D),
                fontSize = 25.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        ModifiedSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 15.dp)
        )

        if (filteredReports.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(id = R.string.noReportsForVerifiedLbl))
            }
        }

        ReportsListVerified(
            reports = filteredReports,
            navigateToDetail = navigateToDetail,
            reportsViewModel = reportsViewModel
        )
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        when (result) {
            is RequestResult.Success -> {
                AlertMessage(
                    modifier = Modifier.padding(end = 16.dp, start = 16.dp),
                    type = AlertType.SUCCESS,
                    message = (result as RequestResult.Success).message
                )
                LaunchedEffect(Unit) {
                    delay(1500)
                    reportsViewModel.resetReportResult()
                }
            }

            is RequestResult.Failure -> {
                AlertMessage(
                    modifier = Modifier.padding(end = 12.dp, start = 12.dp),
                    type = AlertType.ERROR,
                    message = (result as RequestResult.Failure).message
                )
                LaunchedEffect(Unit) {
                    delay(2500)
                    reportsViewModel.resetReportResult()
                }
            }

            else -> {}
        }
    }
}
