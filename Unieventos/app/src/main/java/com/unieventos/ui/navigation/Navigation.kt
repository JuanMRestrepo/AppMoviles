package com.unieventos.ui.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.unieventos.ui.screens.HomeScreen
import com.unieventos.ui.screens.LoginScreen
import com.unieventos.ui.screens.SingUpScreen
import com.unieventos.ui.screens.RestartPassword1
import com.unieventos.ui.screens.RestartPassword2
import com.unieventos.ui.screens.VerificationAccountScreen
import com.unieventos.ui.admins.AdminMenuScreen
import com.unieventos.ui.clientes.UserMenuScreen
import com.unieventos.ui.clientes.tabs.DetailReportTab

@Composable
fun Navigation(){

    val navController = rememberNavController()

    Surface {
        NavHost(
            navController = navController,
            //startDestination = RouteScreen.HomeScreen
            startDestination = RouteScreen.UserMenuScreen
            //startDestination = RouteScreen.AdminMenuScreen
        ) {
            composable <RouteScreen.HomeScreen> {
                HomeScreen(
                    navigateToLogIn = {
                        navController.navigate(RouteScreen.LoginScreen)
                    },
                    navigateToSingUp = {
                        navController.navigate(RouteScreen.SingUpScreen)
                    }
                )
            }

            composable <RouteScreen.LoginScreen> {
                LoginScreen(
                    navigateToRestart = {
                        navController.navigate(RouteScreen.RestartPassword1Screen)
                    },
                    navigateToSingUp = {
                        navController.navigate(RouteScreen.SingUpScreen)
                    },
                    navigateToAdmin = {
                        navController.navigate(RouteScreen.AdminMenuScreen)
                    },
                    navigateToUser = {
                        navController.navigate(RouteScreen.UserMenuScreen)
                    }
                )
            }

            composable <RouteScreen.SingUpScreen> {
                SingUpScreen(
                    navigateToLogIn = {
                        navController.navigate(RouteScreen.LoginScreen)
                    },
                    navigateToVerification = {
                        navController.navigate(RouteScreen.VerificationAccountScreen)
                    }
                )
            }

            composable <RouteScreen.RestartPassword2Screen> {
                RestartPassword2 (
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    navigateToHome = {
                        navController.navigate(RouteScreen.HomeScreen)
                    },
                    navigateToLogIn = {
                        navController.navigate(RouteScreen.LoginScreen)
                    }
                )
            }

            composable <RouteScreen.RestartPassword1Screen> {
                RestartPassword1 (
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    navigateToRestart2 = {
                        navController.navigate(RouteScreen.RestartPassword2Screen)
                    },
                    navigateToHome = {
                        navController.navigate(RouteScreen.HomeScreen)
                    }
                )
            }

            composable <RouteScreen.VerificationAccountScreen> {
                VerificationAccountScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    navigateToHome = {
                        navController.navigate(RouteScreen.HomeScreen)
                    },
                    navigateToLogIn = {
                        navController.navigate(RouteScreen.LoginScreen)
                    }
                )
            }

            composable <RouteScreen.AdminMenuScreen> {
                AdminMenuScreen()
            }

            composable <RouteScreen.UserMenuScreen> {
                UserMenuScreen()
            }

            composable <RouteScreen.ReportDetail> {
                val args = it.toRoute<RouteScreen.ReportDetail>()
                DetailReportTab (
                    id = args.id,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}