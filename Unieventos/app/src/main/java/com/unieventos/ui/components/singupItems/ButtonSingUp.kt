package com.unieventos.ui.components.singupItems

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R

@Composable
fun ButtonSingUp(
    navigateToVerification: () -> Unit,
    infoBtnSignup: String
){
    Button(
        colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = {
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
