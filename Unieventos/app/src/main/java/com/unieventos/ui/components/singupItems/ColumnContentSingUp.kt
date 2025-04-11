package com.unieventos.ui.components.singupItems

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.unieventos.ui.components.ButtonHandlingSing
import com.unieventos.ui.components.DropdownMenu
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.ui.components.TextFieldSing

@Composable
fun ColumnContentSingUp(
    navigateToLogIn: () -> Unit,
    navigateToVerification: () -> Unit
){
    val cities = listOf("Armenia", "Pereira", "Manizales")
    var name by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
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
            label = stringResource(id = R.string.nameLabel)
        )

        Spacer(modifier = Modifier.height(20.dp))

        DropdownMenu(
            modifier = Modifier,
            value = city,
            onValueChange = {
                city = it
            },
            items = cities
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldSing(
            value = address,
            onValueChange = {address = it},
            label = stringResource(id = R.string.addressLabel)
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

        Spacer(modifier = Modifier.height(20.dp))

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
            isPassword = true
        )

        Spacer(modifier = Modifier.height(35.dp))

        ButtonSingUp(
            navigateToVerification = navigateToVerification,
            infoBtnSignup = infoBtnSignup
        )
    }
}
