package com.unieventos.ui.clientes.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unieventos.R
import com.unieventos.ui.clientes.navigation.RouteUserTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavHostController) {

    TopAppBar(
        title = {},
        navigationIcon  = {
            Image(
                painter = painterResource(id = R.drawable.save_report),
                contentDescription = stringResource(id = R.string.imageLogo),
                modifier = Modifier
                    .width(90.dp)
                    .padding(start = 20.dp)
            )
        },
        actions = {
            IconButton(onClick =  {
                navController.navigate(RouteUserTab.Settings)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = stringResource(id = R.string.settingsLbl),
                    tint = Color(0xFEE53935)
                )
            }
        }
    )
}