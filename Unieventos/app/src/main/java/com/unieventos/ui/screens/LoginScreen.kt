package com.unieventos.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R
import com.unieventos.model.Role
import com.unieventos.ui.components.loginItems.ColumnContentLogin
import com.unieventos.ui.components.loginItems.TopImage
import com.unieventos.viewmodel.UsersViewModel

@Composable
fun LoginScreen(
    usersViewModel: UsersViewModel,
    navigateToRestart: () -> Unit,
    navigateToSingUp: () -> Unit,
    navigateToUser: (Role) -> Unit
){
    val context = LocalContext.current
    Scaffold { padding ->
        LoginScreenForm(
            usersViewModel = usersViewModel,
            padding = padding,
            context = context,
            navigateToRestart,
            navigateToSingUp,
            navigateToUser
        )
    }
}

@Composable
fun LoginScreenForm(
    usersViewModel: UsersViewModel,
    padding: PaddingValues,
    context: Context,
    navigateToRestart: () -> Unit,
    navigateToSingUp: () -> Unit,
    navigateToUser: (Role) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(Color(0xF3FFFFFF))
    ) {
        val loginValidation = stringResource(id = R.string.loginValidation)
        TopImage()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier
                    .padding(top = 270.dp)
                    .width(355.dp)
                    .height(560.dp),
                color = Color.White,
                shape = RoundedCornerShape(40.dp)
            ) {
                val infoBtnLogin = stringResource(id = R.string.logInBtn)
                val infoBtnSignup = stringResource(id = R.string.singUpText)

                /*
                Credenciales para accede como usuario o admin

                *ADMINISTRADOR*
                email == "admin@gmail.com" && password == "admin123" -> {
                    navigateToAdmin()
                }

                *USUARIO*
                email == "andres@gmail.com" && password == "andres123" -> {
                    navigateToUser()
                }
                 */

                ColumnContentLogin(
                    usersViewModel = usersViewModel,
                    context = context,
                    loginValidation = loginValidation,
                    navigateToRestart = navigateToRestart,
                    navigateToSingUp = navigateToSingUp,
                    navigateToUser = navigateToUser,
                    infoBtnLogin = infoBtnLogin,
                    infoBtnSignup = infoBtnSignup
                )
            }
        }
    }
}
