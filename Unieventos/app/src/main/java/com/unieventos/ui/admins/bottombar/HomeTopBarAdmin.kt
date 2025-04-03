package com.unieventos.ui.admins.bottombar

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBarAdmin(){
    val context = LocalContext.current
    val mensajeEnConstruccion = stringResource(id = R.string.inProgress)
    TopAppBar(
        title = {},
        navigationIcon  = {
            Image(
                painter = painterResource(id = R.drawable.logo_black),
                contentDescription = "Save Report",
                modifier = Modifier
                    .width(90.dp)
                    .padding(start = 20.dp)
            )
        },
        actions = {
            IconButton(onClick = { Toast.makeText(context, mensajeEnConstruccion, Toast.LENGTH_SHORT).show() }) {
                Icon(
                    modifier = Modifier
                        .padding(end = 20.dp),
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "Settings",
                    tint = Color.Red
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )
    )
}
