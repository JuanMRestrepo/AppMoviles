package com.unieventos.ui.admins

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.unieventos.ui.admins.bottombar.HomeBottomBarAdmin
import com.unieventos.ui.admins.bottombar.HomeTopBarAdmin
import com.unieventos.ui.admins.navigation.AdminNavigation
import com.unieventos.ui.admins.navigation.RouteAdminTab

@Composable
fun AdminMenuScreen(
    logout: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            HomeTopBarAdmin(
                navController = navController,
                logout = logout
            )
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
            navigateToDetail = { id ->
                navController.navigate(RouteAdminTab.ReportDetailAdmin(id))
            },
            logout = logout
        )
    }
}
