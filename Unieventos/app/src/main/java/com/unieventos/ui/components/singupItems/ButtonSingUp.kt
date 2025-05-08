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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R
import com.unieventos.model.User
import com.unieventos.viewmodel.UsersViewModel

@Composable
fun ButtonSingUp(
    user: User,
    usersViewModel: UsersViewModel,
    navigateToVerification: () -> Unit,
    infoBtnSignup: String
){
    val context = LocalContext.current

    Button(
        colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = {
            /* navigateToVerification() */
            usersViewModel.createUser(
                User (
                    id = user.id,
                    name = user.name,
                    email = user.email,
                    password = user.password,
                    role = user.role,
                    phoneNumber = user.phoneNumber,
                    address = user.address
                )
            )
            Toast.makeText(context, "Usuario creado", Toast.LENGTH_SHORT).show()
            navigateToVerification()
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
