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
        getAllUsers()
    }

    fun getAllUsers(){
        viewModelScope.launch {
            _users.value = findAllFirebase()
        }
    }

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

    private suspend fun deleteUserFirebase(userId: String) {
        db.collection("users")
            .document(userId)
            .delete()
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

    private suspend fun updateUserFirebase(user: User) {
        db.collection("users")
            .document(user.id)
            .set(user)
            .await()
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

    fun toggleSaveReport(userId: String, reportId: String) {
        viewModelScope.launch {
            try {
                val users = _users.value.toMutableList()
                val userIndex = users.indexOfFirst { it.id == userId }
                if (userIndex == -1) return@launch

                val user = users[userIndex]
                val isSaved = user.savedReportIds.contains(reportId)

                // Crear nuevo usuario manualmente
                val updatedUser = User(
                    id = user.id,
                    name = user.name,
                    email = user.email,
                    password = user.password,
                    role = user.role,
                    phoneNumber = user.phoneNumber,
                    address = user.address,
                    savedReportIds = user.savedReportIds.toMutableList().apply {
                        if (isSaved) remove(reportId) else add(reportId)
                    }
                )

                // Actualizar Firestore
                db.collection("users")
                    .document(userId)
                    .set(updatedUser)
                    .await()

                // Actualizar estado local
                users[userIndex] = updatedUser
                _users.value = users
                _currentUser.value = updatedUser

            } catch (e: Exception) {
                _registerResult.value = RequestResult.Failure(e.message ?: "Error saving report")
            }
        }
    }

    fun getSavedReportIds(userId: String): List<String> {
        return _users.value.find { it.id == userId }?.savedReportIds ?: emptyList()
    }
}
