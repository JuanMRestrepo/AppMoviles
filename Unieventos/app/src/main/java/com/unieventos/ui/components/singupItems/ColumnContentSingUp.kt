package com.unieventos.ui.components.singupItems

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.model.Role
import com.unieventos.model.User
import com.unieventos.ui.components.ButtonHandlingSing
import com.unieventos.ui.components.DropdownMenu
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.ui.components.TextFieldSing
import com.unieventos.viewmodel.UsersViewModel
import java.util.UUID

@Composable
fun ColumnContentSingUp(
    navigateToLogIn: () -> Unit,
    navigateToVerification: () -> Unit
){
    val scrollState = rememberScrollState()
    val cities = listOf(
        "Arauca", "Armenia", "Barranquilla", "Bogotá",
        "Bucaramanga", "Cali", "Cartagena", "Cúcuta",
        "Florencia", "Ibagué", "Inírida", "Leticia",
        "Manizales", "Medellín", "Mitú", "Mocoa",
        "Montería", "Neiva", "Pasto", "Pereira",
        "Popayán", "Puerto Carreño", "Quibdó", "Riohacha",
        "San Andrés", "San José del Guaviare", "Santa Marta", "Sincelejo",
        "Tunja", "Valledupar", "Villavicencio", "Yopal"
    )

    var name by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val isFormValid = name.isNotBlank() &&
            address.isNotBlank() &&
            phoneNumber.isNotBlank() &&
            email.isNotBlank() &&
            password.length >= 6 &&
            confirmPassword.length >= 6 &&
            password == confirmPassword &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val infoBtnLogin = stringResource(id = R.string.logInBtn)
        val infoBtnSignup = stringResource(id = R.string.singUpText)

        ButtonHandlingSing(
            navigateToLogIn = navigateToLogIn,
            infoBtnLogin = infoBtnLogin,
            infoBtnSignup = infoBtnSignup
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.newAccount),
            color = Color.Black,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldSing(
            value = name,
            onValueChange = {name = it},
            label = stringResource(id = R.string.nameLabel),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )

        Spacer(modifier = Modifier.height(20.dp))

        DropdownMenu(
            modifier = Modifier,
            value = address,
            onValueChange = {
                address = it
            },
            items = cities,
            message = R.string.selectCityLbl
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldSing(
            value = phoneNumber,
            onValueChange = {phoneNumber = it},
            label = stringResource(id = R.string.phoneNumberLabel),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldForm(
            value = email,
            onValueChange = {
                email = it
            },
            supportingText = stringResource(id = R.string.emailValidation),
            label = stringResource(id = R.string.emailLabel),
            onValidate = {
                !Patterns.EMAIL_ADDRESS.matcher(it).matches()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isPassword = false
        )

        TextFieldForm(
            value = password,
            onValueChange = {
                password = it
            },
            supportingText = stringResource(id = R.string.passwordValidation),
            label = stringResource(id = R.string.passwordLabel),
            onValidate = {
                password.length < 6
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true,
            showPassword = passwordVisible,
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = "Toggle password visibility")
                }
            }
        )

        TextFieldForm(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            supportingText = "Las contraseñas deben coincidir y tener al menos 6 caracteres",
            label = "Confirm your password",
            onValidate = {
                (confirmPassword != password || confirmPassword.length < 6)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true,
            showPassword = confirmPasswordVisible,
            trailingIcon = {
                val icon = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(imageVector = icon, contentDescription = "Toggle confirm password visibility")
                }
            }
        )

        Spacer(modifier = Modifier.height(35.dp))

        ButtonSingUp(
            user = User(
                name = name,
                email = email,
                password = password,
                phoneNumber = phoneNumber,
                address = address
            ),
            navigateToVerification = navigateToVerification,
            infoBtnSignup = infoBtnSignup,
            enabled = isFormValid
        )
    }
}
