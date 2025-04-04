package com.unieventos.ui.admins.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R

@Composable
fun ProfileAdminTab(
    navigateToEditAdminProfile: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val changeUser = stringResource(id = R.string.changeUserLbl)
        val editProfile = stringResource(id = R.string.editLbl)
        val saveItems = stringResource(id = R.string.saveItems)
        val activityLbl = stringResource(id = R.string.activityLbl)
        val deleteAccountLbl = stringResource(id = R.string.deleteAccountLbl)
        val logOutBtn = stringResource(id = R.string.logOutBtn)

        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = stringResource(id = R.string.imagePerfil),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.messagePerfilAdmin),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(9.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ProfileOption(text = changeUser, isRed = true)
                Spacer(modifier = Modifier.height(20.dp))
                ProfileOption(text = editProfile, isRed = true,
                    onClick = {
                        navigateToEditAdminProfile()
                    })
                Spacer(modifier = Modifier.height(20.dp))
                ProfileOption(text = saveItems, isRed = true)
                Spacer(modifier = Modifier.height(20.dp))
                ProfileOption(text = activityLbl, isRed = true)
                Spacer(modifier = Modifier.height(20.dp))
                ProfileOption(text = deleteAccountLbl, isRed = true)
            }
        }

        Spacer(modifier = Modifier.height(55.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.Logout,
                contentDescription = stringResource(id = R.string.logoutIcon),
                tint = Color.White
            )
            Text(text = logOutBtn, color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun ProfileOption(
    text: String,
    hasArrow: Boolean = false,
    isRed: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onClick?.invoke()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = if (isRed) Color.Red else Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        if (hasArrow) {
            Icon(Icons.Outlined.ArrowForward, contentDescription = "Arrow", tint = Color.Black)
        }
    }
}
