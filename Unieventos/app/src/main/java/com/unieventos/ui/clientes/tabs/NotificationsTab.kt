package com.unieventos.ui.clientes.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unieventos.ui.clientes.componentsClient.GetTestNotifications
import com.unieventos.ui.clientes.componentsClient.ModifiedSearchBar
import com.unieventos.ui.clientes.componentsClient.NotificationsList

@Composable
fun NotificationsTab(
) {
    val notifications = GetTestNotifications()
    var searchQuery by remember { mutableStateOf("") }

    val filteredReports = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            notifications
        } else {
            notifications.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.message.contains(searchQuery, ignoreCase = true)
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
        NotificationsList( notifications = filteredReports)
    }
}
