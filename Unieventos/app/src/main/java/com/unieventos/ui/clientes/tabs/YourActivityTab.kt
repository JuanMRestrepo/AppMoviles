package com.unieventos.ui.clientes.tabs

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import com.unieventos.ui.clientes.componentsClient.ModifiedSearchBar
import com.unieventos.ui.clientes.componentsClient.ReportListEdit
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.RequestResult
import com.unieventos.utils.SharedPreferencesUtils

@Composable
fun YourActivityTab(
    navigateToDetail: (String) -> Unit,
    navigateToEdit: (String) -> Unit,
    onNavigateBack: () -> Unit
) {

    val context = LocalContext.current
    val reportsViewModel = LocalMainViewModel.current.reportsViewModel
    var searchQuery by remember { mutableStateOf("") }

    val allReports by reportsViewModel.reports.collectAsState()
    val userId = SharedPreferencesUtils.getPreference(context).get("userId")!!

    val userReports by remember(allReports) {
        derivedStateOf { allReports.filter { it.idUser == userId } }
    }

    val reportResult by reportsViewModel.reportResult.collectAsState()

    val filteredReports by remember(searchQuery, userReports) {
        derivedStateOf {
            if (searchQuery.isEmpty()) {
                userReports
            } else {
                userReports.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                            it.description.contains(searchQuery, ignoreCase = true)
                }
            }
        }
    }

    LaunchedEffect(reportResult) {
        when (reportResult) {
            is RequestResult.Success -> {
                Toast.makeText(
                    context,
                    "Reporte eliminado correctamente",
                    Toast.LENGTH_SHORT
                ).show()
                reportsViewModel.resetReportResult()
            }
            is RequestResult.Failure -> {
                Toast.makeText(
                    context,
                    (reportResult as RequestResult.Failure).message,
                    Toast.LENGTH_LONG
                ).show()
                reportsViewModel.resetReportResult()
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.saveLabel),
                tint = Color(0xFFFF4A3D),
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        onNavigateBack()
                    }
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = stringResource(id = R.string.listYourReports),
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
                Text(stringResource(id = R.string.noReportsLbl))
            }
        } else {
            ReportListEdit (
                reports = filteredReports,
                navigateToDetail = navigateToDetail,
                navigateToEdit = navigateToEdit,
                reportsViewModel = reportsViewModel
            )
        }
    }
}