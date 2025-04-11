package com.unieventos.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileOption(
    text: String,
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
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            color = if (isRed) Color.Red else Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
    }
}
