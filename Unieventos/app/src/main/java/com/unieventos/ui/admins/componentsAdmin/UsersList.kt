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
import com.unieventos.model.User

@Composable
fun UsersList(
    users: List<User>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 5.dp)
    ) {
        items(users) { user ->
            UserCardItem(
                user = user
            )
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 1.dp
            )
        }
    }
}