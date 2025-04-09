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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R
import com.unieventos.ui.screens.TestUsers

@Composable
fun ButtonLogin(
    email: String,
    password: String,
    context: Context,
    loginValidation: String,
    infoBtnLogin: String,
    navigateToAdmin: () -> Unit,
    navigateToUser: () -> Unit
){
    Button(
        colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 30.dp)
        ,
        onClick = {
            when {
                email == TestUsers.adminUser.email && password == TestUsers.adminUser.password -> {
                    navigateToAdmin()
                }
                email == TestUsers.normalUser.email && password == TestUsers.normalUser.password -> {
                    navigateToUser()
                }
                else -> {
                    Toast.makeText(context, loginValidation, Toast.LENGTH_SHORT).show()
                }
            }
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
