package com.unieventos.ui.clientes

import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.unieventos.ui.clientes.bottombar.HomeBottomBar
import com.unieventos.ui.clientes.bottombar.HomeTopBar
import com.unieventos.ui.clientes.navigation.RouteUserTab
import com.unieventos.ui.clientes.navigation.UserNavigation

@Composable
fun UserMenuScreen(
    logout: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            HomeTopBar(
                navController = navController,
                logout = logout
            )
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
            navigateToDetail = { id ->
                navController.navigate(RouteUserTab.ReportDetail(id))
            },
            navigateToEdit = { id ->
                navController.navigate(RouteUserTab.ReportDetailEdit(id))
            },
            logout = logout
        )
    }
}
