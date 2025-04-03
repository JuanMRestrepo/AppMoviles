package com.unieventos.ui.admins

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.unieventos.ui.admins.bottombar.HomeBottomBarAdmin
import com.unieventos.ui.admins.bottombar.HomeTopBarAdmin
import com.unieventos.ui.admins.navigation.AdminNavigation

@Composable
fun AdminMenuScreen(
    navigateToDetail: (String) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            HomeTopBarAdmin()
        },
        bottomBar = {
            HomeBottomBarAdmin(
                navController = navController
            )
        }
    ) { paddingValues ->
        AdminNavigation(
            paddingValues = paddingValues,
            navController = navController,
            navigateToDetail = navigateToDetail
        )
    }
}
