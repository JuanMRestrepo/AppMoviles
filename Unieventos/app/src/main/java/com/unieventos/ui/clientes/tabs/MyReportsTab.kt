package com.unieventos.ui.clientes.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.unieventos.ui.clientes.componentsClient.GetTestReports
import com.unieventos.ui.clientes.componentsClient.ModifiedSearchBar
import com.unieventos.ui.clientes.componentsClient.ReportsListStatus

@Composable
fun MyReportsTab(
    navigateToDetail: (String) -> Unit
) {
    val reports = GetTestReports()
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
        ModifiedSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 15.dp)
        )
        ReportsListStatus (reports = filteredReports, navigateToDetail = navigateToDetail)
    }
}
