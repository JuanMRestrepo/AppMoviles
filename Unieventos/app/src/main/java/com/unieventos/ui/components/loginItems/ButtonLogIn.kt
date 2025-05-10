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
import com.unieventos.model.Role
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.ui.screens.TestUsers
import com.unieventos.utils.SharedPreferencesUtils
import com.unieventos.viewmodel.UsersViewModel

@Composable
fun ButtonLogin(
    email: String,
    password: String,
    context: Context,
    loginValidation: String,
    infoBtnLogin: String,
    navigateToUser: (Role) -> Unit
){
    val usersViewModel = LocalMainViewModel.current.usersViewModel

    Button(
        colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 30.dp)
        ,
        onClick = {

            val user = usersViewModel.login(email, password)

            if(user == null){
                Toast.makeText(context, loginValidation, Toast.LENGTH_SHORT).show()
            }else {
                SharedPreferencesUtils.savePreference(context, user.id, user.role)
                navigateToUser(user.role)
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
