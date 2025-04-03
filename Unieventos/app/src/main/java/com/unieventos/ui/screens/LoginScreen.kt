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
import com.unieventos.ui.components.loginItems.ColumnContentLogin
import com.unieventos.ui.components.loginItems.TopImage

@Composable
fun LoginScreen(
    navigateToRestart: () -> Unit,
    navigateToSingUp: () -> Unit,
    navigateToAdmin: () -> Unit,
    navigateToUser: () -> Unit
){
    val context = LocalContext.current
    Scaffold { padding ->
        LoginScreenForm(
            padding = padding,
            context = context,
            navigateToRestart,
            navigateToSingUp,
            navigateToAdmin,
            navigateToUser
        )
    }
}

@Composable
fun LoginScreenForm(
    padding: PaddingValues,
    context: Context,
    navigateToRestart: () -> Unit,
    navigateToSingUp: () -> Unit,
    navigateToAdmin: () -> Unit,
    navigateToUser: () -> Unit
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
                ColumnContentLogin(
                    context = context,
                    loginValidation = loginValidation,
                    navigateToRestart = navigateToRestart,
                    navigateToAdmin = navigateToAdmin,
                    navigateToSingUp = navigateToSingUp,
                    navigateToUser = navigateToUser,
                    infoBtnLogin = infoBtnLogin,
                    infoBtnSignup = infoBtnSignup
                )
            }
        }
    }
}
