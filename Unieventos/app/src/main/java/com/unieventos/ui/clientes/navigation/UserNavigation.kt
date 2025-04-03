package com.unieventos.ui.clientes.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.unieventos.ui.clientes.tabs.CreateReportTab
import com.unieventos.ui.clientes.tabs.HomeUserTab
import com.unieventos.ui.clientes.tabs.MyReportsTab
import com.unieventos.ui.clientes.tabs.NotificationsTab
import com.unieventos.ui.clientes.tabs.ProfileTab

@Composable
fun UserNavigation(
    paddingValues: PaddingValues,
    navController: NavHostController,
    navigateToDetail: (String) -> Unit
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = RouteUserTab.Home
    ) {
        composable <RouteUserTab.Home> {
            HomeUserTab()
        }

        composable <RouteUserTab.Reports> {
            MyReportsTab(
                navigateToDetail = navigateToDetail
            )
        }

        composable <RouteUserTab.CreateReport> {
            CreateReportTab()
        }

        composable <RouteUserTab.Notifications> {
            NotificationsTab()
        }

        composable <RouteUserTab.Profile> {
            ProfileTab()
        }
    }
}
