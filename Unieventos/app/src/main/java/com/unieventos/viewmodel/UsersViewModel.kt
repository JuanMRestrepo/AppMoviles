package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unieventos.model.User
import com.unieventos.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UsersViewModel: ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> = _users.asStateFlow()
    private val db = Firebase.firestore

    private val _registerResult = MutableStateFlow<RequestResult?>(null)
    val registerResult: StateFlow<RequestResult?> = _registerResult.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        getUsers()
    }

    fun createUser(user: User) {
        viewModelScope.launch {
            _registerResult.value = RequestResult.Loading
            _registerResult.value = runCatching { createUserFirebase(user) }
                .fold(
                    onSuccess = {
                        RequestResult.Success("Usuario creado correctamente")
                    },
                    onFailure = {
                        RequestResult.Failure(it.message ?: "Error creating user")
                    }
                )
        }
    }

    private suspend fun createUserFirebase(user: User) {

        val responseUser = auth.createUserWithEmailAndPassword(user.email, user.password).await()
        val userId = responseUser.user?.uid ?: ""

        val userCopy = User(
            id = userId,
            name = user.name,
            email = user.email,
            password = "",
            role = user.role,
            phoneNumber = user.phoneNumber,
            address = user.address
        )

        db.collection("users")
            .document(userId)
            .set(userCopy)
            .await()
    }

    private suspend fun deleteUserFirebase(userId: String) {
        db.collection("users")
            .document(userId)
            .delete()
            .await()
    }

    fun deleteUser(userId: String) {
        viewModelScope.launch {
            _registerResult.value = RequestResult.Loading
            _registerResult.value = runCatching { deleteUserFirebase(userId) }
                .fold(
                    onSuccess = {
                        RequestResult.Success("User eliminated correctly")
                    },
                    onFailure = {
                        RequestResult.Failure(it.message ?: "Error deleting user")
                    }
                )
        }
    }

    private suspend fun updateUserFirebase(user: User) {
        db.collection("users")
            .document(user.id)
            .set(user)
            .await()
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            _registerResult.value = RequestResult.Loading
            _registerResult.value = runCatching { updateUserFirebase(user) }
                .fold(
                    onSuccess = {
                        RequestResult.Success("User updated correctly")
                    },
                    onFailure = {
                        RequestResult.Failure(it.message ?: "Error editing user")
                    }
                )
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _registerResult.value = RequestResult.Loading
            _registerResult.value = runCatching { loginFirebase(email, password) }
                .fold(
                    onSuccess = {
                        RequestResult.Success("User logged succesfully")
                    },
                    onFailure = {
                        RequestResult.Failure(it.message ?: "Error logging user")
                    }
                )
        }
    }

    private suspend fun loginFirebase(email: String, password: String){
        val responseUser = auth.signInWithEmailAndPassword(email, password).await()
        val userId = responseUser.user?.uid ?: ""
        findByIdFirebase(userId)
    }

    fun findById(userId: String) {
        viewModelScope.launch {
            findByIdFirebase(userId)
        }
    }

    private suspend fun findByIdFirebase(userId: String) {
        val querySnapshot = db.collection("users")
            .document(userId)
            .get()
            .await()

        val user = querySnapshot.toObject(User::class.java)?.apply {
            id = querySnapshot.id
        }
        _currentUser.value = user
    }

    fun getNameById(userId: String): String {
        val user = _users.value.find { it.id == userId }
        return user?.name ?: ""
    }

    fun logout(){
        auth.signOut()
        _currentUser.value = null
    }

    fun resetRegisterResult() {
        _registerResult.value = null
    }

    fun getUsers(){
        viewModelScope.launch {
            _users.value = findAllFirebase()
        }
    }

    /*
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

     */

    private suspend fun findAllFirebase(): List<User> {
        val querySnapshot = db.collection("users")
            .get()
            .await()

        return querySnapshot.documents.mapNotNull {
            it.toObject(User::class.java)?.apply {
                id = it.id
            }
        }
    }

}
