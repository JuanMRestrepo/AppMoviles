package com.unieventos.ui.clientes

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.unieventos.ui.clientes.bottombar.HomeBottomBar
import com.unieventos.ui.clientes.navigation.UserNavigation
import com.unieventos.ui.clientes.bottombar.HomeTopBar

@Composable
fun UserMenuScreen(
    navigateToDetail: (String) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            HomeBottomBar(
                navController = navController
            )
        }
    ) { paddingValues ->
        UserNavigation(
            paddingValues = paddingValues,
            navController = navController,
            navigateToDetail = navigateToDetail,
        )
    }
}
