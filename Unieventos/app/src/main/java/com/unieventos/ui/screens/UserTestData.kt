package com.unieventos.ui.screens

import com.unieventos.model.Address
import com.unieventos.model.Role
import com.unieventos.model.User

object TestUsers {
    val adminUser = User(
        id = "1",
        name = "Juan manuel",
        email = "admin@gmail.com",
        password = "admin123",
        role = Role.ADMIN,
        phoneNumber = "+57 1234567890",
        address = Address(
            street = "Calle 123",
            city = "Medellín",
            state = "Antioquia",
            postalCode = "050010",
            country = "Colombia"
        )
    )

    val normalUser = User(
        id = "2",
        name = "Andrés Díaz",
        email = "andres@gmail.com",
        password = "andres123",
        role = Role.CLIENT,
        phoneNumber = "+57 3101234567",
        address = Address(
            street = "Calle 123",
            city = "Medellín",
            state = "Antioquia",
            postalCode = "050010",
            country = "Colombia"
        )
    )
}