package com.unieventos.ui.admins.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.unieventos.R

@Composable
fun HomeAdminTab(){

    val imageUrl = "https://okdiario.com/img/2024/10/21/rutas-personalizadas-5-e1729497886596.jpg"

    Column {
        Text(
            text = stringResource(id = R.string.messagePerfilAdmin),
        )
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = stringResource(id = R.string.mapImage),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

