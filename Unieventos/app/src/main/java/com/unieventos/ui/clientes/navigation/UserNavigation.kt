package com.unieventos.ui.clientes.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.unieventos.ui.clientes.tabs.CreateReportTab
import com.unieventos.ui.clientes.tabs.DetailReportTab
import com.unieventos.ui.clientes.tabs.EditProfileTab
import com.unieventos.ui.clientes.tabs.EditReportTab
import com.unieventos.ui.clientes.tabs.HomeUserTab
import com.unieventos.ui.clientes.tabs.MyReportsTab
import com.unieventos.ui.clientes.tabs.NotificationsTab
import com.unieventos.ui.clientes.tabs.ProfileTab
import com.unieventos.ui.clientes.tabs.SavedItemsTab
import com.unieventos.ui.clientes.tabs.YourActivityTab
import com.unieventos.ui.screens.SettingsTab

@Composable
fun UserNavigation(
    paddingValues: PaddingValues,
    navController: NavHostController,
    navigateToDetail: (String) -> Unit,
    navigateToEdit: (String) -> Unit
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = RouteUserTab.Home
    ) {
        composable <RouteUserTab.Home> {
            HomeUserTab(
                onNavigateToDetail = navigateToDetail
            )
        }

        composable <RouteUserTab.Reports> {
            MyReportsTab(
                navigateToDetail = navigateToDetail
            )
        }

        composable <RouteUserTab.CreateReport> {
            CreateReportTab(
                navigateToLogIn = {
                    navController.popBackStack()
                }
            )
        }

        composable <RouteUserTab.Notifications> {
            NotificationsTab()
        }

        composable <RouteUserTab.Profile> {
            ProfileTab(
                navigateToEditProfile = {
                    navController.navigate(RouteUserTab.EditProfile)
                },
                navigateToSavedItemsTab = {
                    navController.navigate(RouteUserTab.SavedItems)
                },
                navigateToYourActivity = {
                    navController.navigate(RouteUserTab.YourActivity)
                }
            )
        }

        composable <RouteUserTab.EditProfile> {
            EditProfileTab()
        }

        composable <RouteUserTab.SavedItems> {
            SavedItemsTab(
                navigateToDetail = navigateToDetail
            )
        }

        composable <RouteUserTab.YourActivity> {
            YourActivityTab(
                navigateToDetail = navigateToDetail,
                navigateToEdit = navigateToEdit
            )
        }

        composable <RouteUserTab.Settings>  {
            SettingsTab()
        }

        composable <RouteUserTab.ReportDetail> {
            val args = it.toRoute<RouteUserTab.ReportDetail>()
            DetailReportTab (
                id = args.id,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable <RouteUserTab.ReportDetailEdit> {
            val args = it.toRoute<RouteUserTab.ReportDetailEdit>()
            EditReportTab (
                id = args.id,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
