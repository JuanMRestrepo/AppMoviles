package com.unieventos.ui.admins.componentsAdmin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.unieventos.model.Address
import com.unieventos.model.Role
import com.unieventos.model.User

@Composable
fun GetTestUsers(): List<User> {
    return remember {
        listOf(
            User(
                id = "1001",
                name = "Juan Pérez",
                email = "juan.perez@example.com",
                password = "password123",
                role = Role.CLIENT,
                phoneNumber = "+57 310 123 4567",
                address = "br"
            ),
            User(
                id = "2002",
                name = "María González",
                email = "maria.gonzalez@example.com",
                password = "securePass456",
                role = Role.CLIENT,
                phoneNumber = "+57 315 987 6543",
                address = "br"
            ),
            User(
                id = "3003",
                name = "Carlos Rodríguez",
                email = "carlos.rodriguez@organizador.com",
                password = "eventOrg789",
                role = Role.CLIENT,
                phoneNumber = "+57 320 456 7890",
                address = "br"
            ),
            User(
                id = "4004",
                name = "Admin Sistema",
                email = "admin@unieventos.com",
                password = "AdminSecure123",
                role = Role.CLIENT,
                phoneNumber = "+57 1 234 5678",
                address = "br"
            ),
            User(
                id = "5005",
                name = "Ana López",
                email = "ana.lopez@example.com",
                password = "anaPassword789",
                role = Role.ADMIN,
                phoneNumber = "+57 317 654 3210",
                address = "br"
            )
        )
    }
}