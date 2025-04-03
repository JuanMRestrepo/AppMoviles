package com.unieventos.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R

@Composable
fun TopBarDefect(
    navigateToSingUp: () -> Unit,
    navigateToHome: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFEE53935)),
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton (
                onClick = {
                    navigateToSingUp()
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = android.R.drawable.ic_menu_revert),
                    contentDescription = stringResource(id = R.string.returnIcon),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    navigateToHome()
                }
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = stringResource(id = R.string.homeIcon),
                    tint = Color.White
                )
            }
        }
    }
}