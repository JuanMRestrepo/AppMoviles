package com.unieventos.ui.admins.tabs

import androidx.compose.foundation.background
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
import com.unieventos.ui.admins.componentsAdmin.GetTestUsers
import com.unieventos.ui.admins.componentsAdmin.UsersList
import com.unieventos.ui.clientes.componentsClient.ModifiedSearchBar

@Composable
fun UsersAdminTab(
) {
    val users = GetTestUsers()
    var searchQuery by remember { mutableStateOf("") }

    val filteredReports = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            users
        } else {
            users.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                        it.id.contains(searchQuery, ignoreCase = true)
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
        UsersList( users = filteredReports)
    }
}
