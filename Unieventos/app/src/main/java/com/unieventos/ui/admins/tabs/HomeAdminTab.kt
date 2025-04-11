package com.unieventos.ui.admins.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R

@Composable
fun HomeAdminTab(){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.mapavertical),
            contentDescription = stringResource(id = R.string.mapImage),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = stringResource(id = R.string.currentLocationLbl),
            tint = Color.Red,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center)
                .offset(y = (-24).dp)
        )

        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color(0x330000FF), shape = CircleShape)
                .align(Alignment.Center)
                .offset(y = (-12).dp)
        )
    }
}

