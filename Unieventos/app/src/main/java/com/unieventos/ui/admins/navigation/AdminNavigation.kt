package com.unieventos.ui.admins.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.unieventos.ui.admins.componentsAdmin.ReportDetailAdmin
import com.unieventos.ui.admins.tabs.AllReportsTab
import com.unieventos.ui.admins.tabs.EditProfileAdminTab
import com.unieventos.ui.admins.tabs.HomeAdminTab
import com.unieventos.ui.admins.tabs.ProfileAdminTab
import com.unieventos.ui.admins.tabs.ReportVerifiedTab
import com.unieventos.ui.admins.tabs.StaticsTab
import com.unieventos.ui.admins.tabs.UsersAdminTab
import com.unieventos.ui.clientes.tabs.DetailReportTab
import com.unieventos.ui.clientes.tabs.EditReportTab
import com.unieventos.ui.screens.SettingsTab

@Composable
fun AdminNavigation(
    paddingValues: PaddingValues,
    navController: NavHostController,
    navigateToDetail: (String) -> Unit,
    logout: () -> Unit
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = RouteAdminTab.HomeAdmin
    ) {
        composable<RouteAdminTab.HomeAdmin> {
            HomeAdminTab(
                onNavigateToDetail = navigateToDetail
            )
        }

        composable<RouteAdminTab.UsersAdmin> {
            UsersAdminTab()
        }

        composable<RouteAdminTab.ReportsAdmin> {
            ReportVerifiedTab(
                navigateToDetail = navigateToDetail
            )
        }

        composable<RouteAdminTab.StaticsAdmin> {
            StaticsTab()
        }

        composable<RouteAdminTab.ProfileAdmin> {
            ProfileAdminTab(
                navigateToEditAdminProfile = {
                    navController.navigate(RouteAdminTab.EditProfileAdmin)
                },
                navigateToAllReports = {
                    navController.navigate(RouteAdminTab.AllReports)
                },
                navigateToLogIn = {
                    logout()
                }
            )
        }

        composable<RouteAdminTab.EditProfileAdmin> {
            EditProfileAdminTab(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<RouteAdminTab.AllReports> {
            AllReportsTab (
                navigateToDetail = navigateToDetail,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable <RouteAdminTab.Setting>  {
            SettingsTab()
        }

        composable <RouteAdminTab.ReportDetailAdmin> {
            val args = it.toRoute<RouteAdminTab.ReportDetailAdmin>()

            ReportDetailAdmin(
                id = args.id,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
