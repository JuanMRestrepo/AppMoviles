package com.unieventos.ui.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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
import com.unieventos.utils.SharedPreferencesUtils
import com.unieventos.viewmodel.UsersViewModel
import com.unieventos.model.Role

@Composable
fun Navigation(
    usersViewModel: UsersViewModel
){

    val context = LocalContext.current
    val navController = rememberNavController()

    val user = SharedPreferencesUtils.getPreference(context)
    var startDestination: RouteScreen = RouteScreen.LoginScreen

    if(user.isNotEmpty()){
        val role = user.get("role")
        startDestination = if(role == "ADMIN"){
            RouteScreen.AdminMenuScreen
        }else {
            RouteScreen.UserMenuScreen
        }
    }

    Surface {
        NavHost(
            navController = navController,
            startDestination = startDestination
            //startDestination = RouteScreen.UserMenuScreen
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
                    usersViewModel = usersViewModel,
                    navigateToRestart = {
                        navController.navigate(RouteScreen.RestartPassword1Screen)
                    },
                    navigateToSingUp = {
                        navController.navigate(RouteScreen.SingUpScreen)
                    },
                    navigateToUser = { role ->
                        if(role == Role.ADMIN){
                            navController.navigate(RouteScreen.AdminMenuScreen)
                        }else {
                            navController.navigate(RouteScreen.UserMenuScreen)
                        }
                    }
                )
            }

            composable <RouteScreen.SingUpScreen> {
                SingUpScreen(
                    usersViewModel = usersViewModel,
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
                AdminMenuScreen(
                    logout = {
                        SharedPreferencesUtils.clearPreference(context)
                        navController.navigate(RouteScreen.LoginScreen)
                    }
                )
            }

            composable <RouteScreen.UserMenuScreen> {
                UserMenuScreen(
                    logout = {
                        SharedPreferencesUtils.clearPreference(context)
                        navController.navigate(RouteScreen.LoginScreen)
                    }
                )
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