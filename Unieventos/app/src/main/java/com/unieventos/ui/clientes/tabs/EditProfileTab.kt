package com.unieventos.ui.clientes.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.SharedPreferencesUtils

@Composable
fun EditProfileTab(
) {

    val context = LocalContext.current
    val usersViewModel = LocalMainViewModel.current.usersViewModel

    val userMap = SharedPreferencesUtils.getPreference(context)
    val userId = userMap.get("userId") ?: ""

    val userName = usersViewModel.getNameById(userId)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val nameLabel = stringResource(id = R.string.fullNameLbl)
            val cityLabel = stringResource(id = R.string.cityOfResidenceLbl)
            val addressLabel = stringResource(id = R.string.addressLabel)
            val emailLabel = stringResource(id = R.string.emailLabel)

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.fondo),
                contentDescription = stringResource(id = R.string.imagePerfil),
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = userName,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(40.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ProfileInfoRow(nameLabel, "C durate")
                    ProfileInfoRow(cityLabel, "Armenia")
                    ProfileInfoRow(addressLabel, "Calle 8 Cra 9 7-73")
                    ProfileInfoRow(emailLabel, "diazandreslm10@gmail.com")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.messageEdit),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                modifier = Modifier
                    .width(290.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(text = stringResource(id = R.string.editInformation),
                    fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}