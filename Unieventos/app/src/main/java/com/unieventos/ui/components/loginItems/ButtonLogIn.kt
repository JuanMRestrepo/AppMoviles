package com.unieventos.ui.components.loginItems

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R
import com.unieventos.model.Role
import com.unieventos.ui.components.AlertMessage
import com.unieventos.ui.components.AlertType
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.ui.screens.TestUsers
import com.unieventos.utils.RequestResult
import com.unieventos.utils.SharedPreferencesUtils
import com.unieventos.viewmodel.UsersViewModel
import kotlinx.coroutines.delay

@Composable
fun ButtonLogin(
    email: String,
    password: String,
    context: Context,
    loginValidation: String,
    infoBtnLogin: String,
    navigateToUser: () -> Unit
){
    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val registerResult by usersViewModel.registerResult.collectAsState()

    when(registerResult){
        null ->{
        }
        is RequestResult.Success ->{
            AlertMessage(
                modifier = Modifier.padding(end = 16.dp, start = 16.dp),
                type = AlertType.SUCCESS,
                message = (registerResult as RequestResult.Success).message
            )
            LaunchedEffect(Unit) {
                delay(2000)
                navigateToUser()
                usersViewModel.resetRegisterResult()
            }
        }
        is RequestResult.Failure ->{
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
        is RequestResult.Loading ->{
            LinearProgressIndicator()
        }
    }

    /*
    when(registerResult){
        null ->{
        }
        is RequestResult.Success ->{
            Toast.makeText(context, (registerResult as RequestResult.Success).message, Toast.LENGTH_SHORT).show()
            LaunchedEffect(Unit) {
                delay(300)
                usersViewModel.resetRegisterResult()
                navigateToUser()
            }
        }
        is RequestResult.Failure ->{
            Toast.makeText(context, (registerResult as RequestResult.Failure).message, Toast.LENGTH_SHORT).show()
            LaunchedEffect(Unit) {
                delay(300)
                usersViewModel.resetRegisterResult()
            }
        }
        is RequestResult.Loading ->{
            LinearProgressIndicator()
        }
    }
     */


    Button(
        colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 30.dp)
        ,
        onClick = {
            usersViewModel.login(email, password)
        },
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.Login,
            contentDescription = stringResource(id = R.string.loginIcon),
            tint = Color.White
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(infoBtnLogin, color = Color.White)
    }
}
