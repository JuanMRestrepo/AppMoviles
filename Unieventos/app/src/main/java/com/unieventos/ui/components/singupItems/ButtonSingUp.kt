package com.unieventos.ui.components.singupItems

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.RequestResult
import kotlinx.coroutines.delay

@Composable
fun ButtonSingUp(
    user: User,
    navigateToVerification: () -> Unit,
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
            Toast.makeText(context, (registerResult as RequestResult.Success).message, Toast.LENGTH_SHORT).show()
            LaunchedEffect(Unit) {
                delay(300)
                usersViewModel.resetRegisterResult()
                navigateToVerification()
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
