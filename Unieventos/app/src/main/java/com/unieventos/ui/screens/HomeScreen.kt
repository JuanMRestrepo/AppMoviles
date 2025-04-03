package com.unieventos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.unieventos.ui.components.homeItems.BackgroundImageEfect
import com.unieventos.ui.components.homeItems.ColumnContentHome

@Composable
fun HomeScreen(
    navigateToLogIn: () -> Unit,
    navigateToSingUp: () -> Unit
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            BackgroundImageEfect()
            ColumnContentHome (
                navigateToLogIn = navigateToLogIn,
                navigateToSingUp = navigateToSingUp
            )
        }
    }
}
