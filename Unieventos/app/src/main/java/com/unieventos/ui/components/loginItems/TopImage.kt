package com.unieventos.ui.components.loginItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R

@Composable
fun TopImage(){
    Image(
        painter = painterResource(id = R.drawable.fondo),
        contentDescription = stringResource(id = R.string.imageFond),
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .clip(
                RoundedCornerShape(
                    bottomStart = 40.dp,
                    bottomEnd = 40.dp
                )
            ),
        contentScale = ContentScale.Crop
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(
                color = Color(0xFFFF4B3A).copy(alpha = 0.35f),
                shape = RoundedCornerShape(
                    bottomStart = 40.dp,
                    bottomEnd = 40.dp
                )
            ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_report),
            contentDescription = stringResource(id = R.string.imageLogo),
            modifier = Modifier
                .width(200.dp)
                .height(220.dp)
                .align(Alignment.Center)
                .offset(y = (-60).dp)
        )
    }
}
