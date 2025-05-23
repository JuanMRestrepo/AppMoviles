package com.unieventos.ui.components.singupItems

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R
import com.unieventos.model.User
import com.unieventos.ui.components.AlertMessage
import com.unieventos.ui.components.AlertType
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.RequestResult
import kotlinx.coroutines.delay

@Composable
fun ButtonSingUp(
    user: User,
    navigateToLogIn: () -> Unit,
    infoBtnSignup: String,
    enabled: Boolean
){
    val context = LocalContext.current
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
                usersViewModel.resetRegisterResult()
                navigateToLogIn()
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

    Button(
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = {
            /* navigateToVerification() */
            usersViewModel.createUser(
                User (
                    name = user.name,
                    email = user.email,
                    password = user.password,
                    phoneNumber = user.phoneNumber,
                    address = user.address
                )
            )
        },
    ) {
        Icon(
            imageVector = Icons.Default.PersonAdd,
            contentDescription = stringResource(id = R.string.singUpIcon),
            tint = Color.White
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(infoBtnSignup, color = Color.White)
    }
}
