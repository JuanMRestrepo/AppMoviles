package com.unieventos.ui.components.loginItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonHandling (
    navigateToSingUp: () -> Unit,
    infoBtnSignup: String,
    infoBtnLogin: String
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    .align(Alignment.CenterEnd),
                colors = ButtonDefaults.buttonColors(Color(0xFFD3CED5)),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    navigateToSingUp()
                },
            ) {
                Text(infoBtnSignup, color = Color.White)
            }
            Button(
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .align(Alignment.CenterStart),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                }
            ) {
                Text(infoBtnLogin, color = Color.White)
            }
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
}