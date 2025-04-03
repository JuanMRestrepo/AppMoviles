package com.unieventos.ui.components.loginItems

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.clickable
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
import com.unieventos.ui.components.TextFieldForm

@Composable
fun ColumnContentLogin(
    context: Context,
    loginValidation: String,
    navigateToRestart: () -> Unit,
    navigateToAdmin: () -> Unit,
    navigateToSingUp: () -> Unit,
    navigateToUser: () -> Unit,
    infoBtnLogin: String,
    infoBtnSignup: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        ButtonHandling(
            navigateToSingUp = navigateToSingUp,
            infoBtnSignup = infoBtnSignup,
            infoBtnLogin = infoBtnLogin
        )

        Text(
            text = stringResource(id = R.string.welcomeHome),
            color = Color.Black,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

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

        Spacer(modifier = Modifier.height(10.dp))

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

        ButtonLogin (
            email = email,
            password = password,
            context = context,
            loginValidation = loginValidation,
            infoBtnLogin = infoBtnLogin,
            navigateToAdmin = navigateToAdmin,
            navigateToUser =  navigateToUser,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.forgotPswd),
            color = Color.Black,
            modifier = Modifier.clickable {
                navigateToRestart()
            }
        )
    }
}
