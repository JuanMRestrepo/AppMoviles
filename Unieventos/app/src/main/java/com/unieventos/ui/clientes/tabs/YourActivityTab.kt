package com.unieventos.ui.clientes.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.unieventos.ui.clientes.componentsClient.ModifiedSearchBar
import com.unieventos.ui.clientes.componentsClient.ReportListEdit
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.SharedPreferencesUtils

@Composable
fun YourActivityTab(
    navigateToDetail: (String) -> Unit,
    navigateToEdit: (String) -> Unit
) {
    val context = LocalContext.current

    val reportsViewModel = LocalMainViewModel.current.reportsViewModel
    val userMap = SharedPreferencesUtils.getPreference(context)
    val reports = reportsViewModel.findByUserId(userMap.get("userId")!!)
    var searchQuery by remember { mutableStateOf("") }

    val filteredReports = remember(searchQuery) {

        if (searchQuery.isEmpty()) {
            reports
        } else {
            reports.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.description.contains(searchQuery, ignoreCase = true)
            }
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
                navigateToEdit = navigateToEdit)
        }
    }
}
