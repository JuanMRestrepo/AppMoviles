package com.unieventos.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ItemText (
    supportingText:String,
){
    Text(
        text = supportingText,
        color = Color.Black,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default
    )
}
