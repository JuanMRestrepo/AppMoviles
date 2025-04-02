package com.unieventos.ui.screens

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.unieventos.R
import com.unieventos.ui.components.TextFieldForm

@Composable
fun LoginAntiguoScreen(){

    val context = LocalContext.current

    Scaffold { padding ->
        LoginAntForm(
            padding = padding,
            context = context
        )
    }
}

@Composable
fun LoginAntForm(
    padding: PaddingValues,
    context: Context
){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

        Spacer(modifier = Modifier.height(10.dp))

        Row {

            val mensajeBienvenido = stringResource(id = R.string.welcomeMessage)
            val mensageValidacion = stringResource(id = R.string.loginValidation)
            val mensajeEnConstruccion = stringResource(id = R.string.singUpIcon)

            Button(
                enabled = email.isNotEmpty() && password.isNotEmpty(),
                onClick = {
                    if(email == "carlos@gmail.com" && password == "123456"){
                        Toast.makeText(context, mensajeBienvenido, Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(context, mensageValidacion, Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon (
                    imageVector = Icons.Rounded.Person,
                    contentDescription = stringResource(id = R.string.emailLabel)
                )
                Text(text = stringResource(id = R.string.logInBtn))
            }

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                onClick = {
                    Toast.makeText(context, mensajeEnConstruccion, Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon (
                    imageVector = Icons.Rounded.Face,
                    contentDescription = stringResource(id = R.string.emailLabel)
                )
                Text(text = stringResource(id = R.string.welcomeMessage))
            }
        }
    }
}