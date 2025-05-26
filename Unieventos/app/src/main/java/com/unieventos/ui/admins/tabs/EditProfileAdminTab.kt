package com.unieventos.ui.admins.tabs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.model.User
import com.unieventos.ui.clientes.componentsClient.PasswordVerificationDialog
import com.unieventos.ui.components.AlertMessage
import com.unieventos.ui.components.AlertType
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.RequestResult
import com.unieventos.utils.SharedPreferencesUtils
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileAdminTab(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val userMap = SharedPreferencesUtils.getPreference(context)
    val userId = userMap.get("userId") ?: ""

    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val currentUser by usersViewModel.currentUser.collectAsState()

    var email by rememberSaveable { mutableStateOf(currentUser?.email ?: "") }
    var name by rememberSaveable { mutableStateOf(currentUser?.name ?: "") }
    var phoneNumber by rememberSaveable { mutableStateOf(currentUser?.phoneNumber ?: "") }
    var address by rememberSaveable { mutableStateOf(currentUser?.address ?: "") }

    val registerResult by usersViewModel.registerResult.collectAsState()
    var showPasswordDialog by rememberSaveable { mutableStateOf(false) }
    var editEnabled by rememberSaveable { mutableStateOf(false) }

    if (showPasswordDialog) {
        PasswordVerificationDialog(
            onVerified = {
                editEnabled = true
                showPasswordDialog = false
            },
            onDismiss = { showPasswordDialog = false }
        )
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(
                    "Editar Perfil",
                    color = Color(0xFFFF4A3D),
                    fontSize = 20.sp
                ) },
                navigationIcon = {
                    IconButton (
                        onClick = onBack,
                    ) {
                        Icon(
                            Icons.Default.ArrowBack, "Regresar",
                            tint = Color(0xFFFF4A3D),
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ){

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fondo),
                    contentDescription = stringResource(id = R.string.imagePerfil),
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (!editEnabled) {
                    Button(
                        onClick = { showPasswordDialog = true },
                        modifier = Modifier.width(250.dp).height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF4A3D),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Habilitar Edición")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre Completo") },
                    enabled = editEnabled,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Teléfono") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    enabled = editEnabled,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Dirección") },
                    enabled = editEnabled,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo Electrónico") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {

                        if (name.isBlank() || phoneNumber.isBlank() || address.isBlank() || email.isBlank()) {
                            Toast.makeText(context, "Complete todos los campos obligatorios", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        currentUser?.let { user ->
                            val updatedUser = User(
                                id = user.id,
                                name = name,
                                email = email,
                                password = user.password,
                                role = user.role,
                                phoneNumber = phoneNumber,
                                address = address,
                                savedReportIds = user.savedReportIds
                            )

                            usersViewModel.updateUserProfile(
                                updatedUser = updatedUser
                            )
                        }
                    },
                    enabled = editEnabled,
                    modifier = Modifier.width(250.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF4A3D),
                        contentColor = Color.White
                    )
                ) {
                    Text("Guardar cambios")
                }
                when (registerResult) {
                    null ->{
                    }
                    is RequestResult.Success -> {
                        AlertMessage(
                            modifier = Modifier.padding(end = 16.dp, start = 16.dp),
                            type = AlertType.SUCCESS,
                            message = (registerResult as RequestResult.Success).message
                        )
                        LaunchedEffect(Unit) {
                            delay(3000)
                            usersViewModel.resetRegisterResult()
                            onBack()
                        }
                    }
                    is RequestResult.Failure -> {
                        AlertMessage(
                            modifier = Modifier.padding(end = 12.dp, start = 12.dp),
                            type = AlertType.ERROR,
                            message = (registerResult as RequestResult.Failure).message
                        )
                        LaunchedEffect(Unit) {
                            delay(3500)
                            usersViewModel.resetRegisterResult()
                        }
                    }
                    is RequestResult.Loading -> {
                        LinearProgressIndicator()
                    }
                }
            }
        }
    }
}
