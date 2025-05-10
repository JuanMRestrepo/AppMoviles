package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.unieventos.model.Role
import com.unieventos.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class UsersViewModel: ViewModel() {

    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        _users.value = getUsers()
    }

    fun createUser(user: User) {
        _users.value += user
    }

    fun deleteUser(userId: String) {
        _users.value = _users.value.filter { it.id != userId }
    }

    fun login(email: String, password: String): User? {
        return _users.value.find { it.email == email && it.password == password }
    }

    fun findById(userId: String): User? {
        return _users.value.find { it.id == userId }
    }

    fun getNameById(userId: String): String {
        val user = _users.value.find { it.id == userId }
        return user?.name ?: ""
    }

    fun getUsers(): List<User> {
        return listOf(
            User(
                "1095550822",
                "andres",
                "andres@gmail.com",
                "123456",
                Role.CLIENT,
                "3137975273",
                "calle 8 cra 9"
            ),
            User(
                "52535115",
                "juan",
                "juan@gmail.com",
                "654321",
                Role.CLIENT,
                "3217565821",
                "calle 2 cra 1"
            ),
            User(
                "93340449",
                "admin",
                "admin@gmail.com",
                "123456",
                Role.ADMIN,
                "3138821382",
                "calle 8 cra 9"
            )
        )
    }
}
