package com.unieventos.ui.clientes.tabs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.unieventos.ui.components.ItemDeleteAccount
import com.unieventos.ui.components.ProfileOption
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.RequestResult
import com.unieventos.utils.SharedPreferencesUtils

@Composable
fun ProfileTab(
    navigateToEditProfile: () -> Unit,
    navigateToSavedItemsTab: () -> Unit,
    navigateToYourActivity: () -> Unit,
    navigateToLogIn: () -> Unit
){
    val context = LocalContext.current
    val usersViewModel = LocalMainViewModel.current.usersViewModel

    val registerResult by usersViewModel.registerResult.collectAsState()

    val userMap = SharedPreferencesUtils.getPreference(context)
    val userId = userMap.get("userId") ?: ""

    val userName = usersViewModel.getNameById(userId)

    LaunchedEffect(registerResult) {
        when (registerResult) {
            is RequestResult.Success -> {
                navigateToLogIn()
                usersViewModel.resetRegisterResult()
            }
            is RequestResult.Failure -> {
                usersViewModel.resetRegisterResult()
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
                .size(130.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Hello! $userName",
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
            var showDeleteDialog by rememberSaveable { mutableStateOf(false) }
            Column(modifier = Modifier.padding(16.dp)) {
                /*
                ProfileOption(
                    text = changeUser,
                    isRed = true
                )
                 */
                Spacer(modifier = Modifier.height(5.dp))

                ProfileOption(
                    text = editProfile,
                    isRed = true,
                    onClick = { navigateToEditProfile() })
                Spacer(modifier = Modifier.height(20.dp))

                ProfileOption(
                    text = saveItems,
                    isRed = true,
                    onClick = { navigateToSavedItemsTab() })
                Spacer(modifier = Modifier.height(20.dp))

                ProfileOption(
                    text = activityLbl,
                    isRed = true,
                    onClick = { navigateToYourActivity() })
                Spacer(modifier = Modifier.height(20.dp))

                ProfileOption(
                    text = deleteAccountLbl,
                    isRed = true,
                    onClick = { showDeleteDialog = true }
                )
            }
            ItemDeleteAccount(
                showDeleteDialog = showDeleteDialog,
                onDismissRequest = { showDeleteDialog = false },
                onConfirm = {
                    usersViewModel.deleteUser(userId)
                }
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = {
                navigateToLogIn()
            },
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

