package com.unieventos.ui.admins.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.unieventos.ui.admins.componentsAdmin.UsersList
import com.unieventos.ui.clientes.componentsClient.ModifiedSearchBar
import com.unieventos.ui.navigation.LocalMainViewModel

@Composable
fun UsersAdminTab(
) {
    val context = LocalContext.current

    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val users by usersViewModel.users.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredUsers by remember {
        derivedStateOf {
            val clients = users.filter { it.role.toString() == "CLIENT" }

            if (searchQuery.isEmpty()) {
                clients
            } else {
                clients.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }
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
        UsersList(
            users = filteredUsers
        )
    }
}
