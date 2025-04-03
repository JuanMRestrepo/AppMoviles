package com.unieventos.ui.components.homeItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R

@Composable
fun ColumnContentHome(
    navigateToLogIn: () -> Unit,
    navigateToSingUp: () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val infoBoton = stringResource(id = R.string.logInBtn)

        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_report),
            contentDescription = stringResource(id = R.string.imageLogo),
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(400.dp))

        Button (
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF4B3A),
                contentColor = Color.White
            ),
            onClick = {
                navigateToLogIn()
            }
        ) {
            Text(text = infoBoton, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(
                text = stringResource(id = R.string.noAccount),
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.singUpText),
                color = Color(0xFFFF4B3A),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navigateToSingUp()
                }
            )
        }
    }
}
