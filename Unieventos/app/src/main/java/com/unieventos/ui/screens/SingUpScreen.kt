package com.unieventos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.unieventos.ui.components.singupItems.ColumnContentSingUp
import com.unieventos.ui.components.singupItems.TopImageSingUp

@Composable
fun SingUpScreen(
    navigateToLogIn: () -> Unit,
    navigateToVerification: () -> Unit
) {
    Scaffold { padding ->
        SingUpForm(padding, navigateToLogIn, navigateToVerification)
    }
}

@Composable
fun SingUpForm(
    padding: PaddingValues,
    navigateToLogIn: () -> Unit,
    navigateToVerification: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6E6E6))
            .padding(padding)
    ) {
        TopImageSingUp()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier
                    .padding(top = 130.dp)
                    .width(355.dp)
                    .height(700.dp),
                color = Color.White,
                shape = RoundedCornerShape(40.dp)
            ) {
                ColumnContentSingUp (
                    navigateToLogIn = navigateToLogIn,
                    navigateToVerification = navigateToVerification
                )
            }
        }
    }
}
