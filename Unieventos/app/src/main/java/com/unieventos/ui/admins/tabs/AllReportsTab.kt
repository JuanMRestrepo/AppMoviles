package com.unieventos.ui.admins.tabs

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.ui.admins.componentsAdmin.AllReportsList
import com.unieventos.ui.clientes.componentsClient.ModifiedSearchBar
import com.unieventos.ui.navigation.LocalMainViewModel

@Composable
fun AllReportsTab(
    navigateToDetail: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val reportsViewModel = LocalMainViewModel.current.reportsViewModel
    val reports by reportsViewModel.reports.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredReports = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            reports
        } else {
            reports.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                    it.description.contains(searchQuery, ignoreCase = true) ||
                        it.state.toString().contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 15.dp)
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
                text = stringResource(id = R.string.allReportsList),
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
        AllReportsList (reports = filteredReports, navigateToDetail = navigateToDetail)
    }
}
