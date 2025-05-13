package com.unieventos.ui.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.auth.FirebaseAuth
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
import com.unieventos.model.Role
import com.unieventos.viewmodel.MainViewModel

val LocalMainViewModel = staticCompositionLocalOf<MainViewModel> { error("MainViewMdel is not provided") }

@Composable
fun Navigation(
    mainViewModel: MainViewModel
){
    val context = LocalContext.current
    val navController = rememberNavController()

    val user = SharedPreferencesUtils.getPreference(context)
    var startDestination: RouteScreen = RouteScreen.HomeScreen

    if(user.isNotEmpty()){
        val role = user.get("role")
        startDestination = if(role == "ADMIN"){
            RouteScreen.AdminMenuScreen
        }else {
            RouteScreen.UserMenuScreen
        }
    }

    Surface {
        CompositionLocalProvider(LocalMainViewModel provides mainViewModel){

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
                        navigateToRestart = {
                            navController.navigate(RouteScreen.RestartPassword1Screen)
                        },
                        navigateToSingUp = {
                            navController.navigate(RouteScreen.SingUpScreen)
                        },
                        navigateToUser = {

                            val currentUser = mainViewModel.usersViewModel.currentUser.value

                            if(currentUser != null){
                                SharedPreferencesUtils.savePreference(context, currentUser.id, currentUser.role)

                                val home = if(currentUser.role == Role.ADMIN){
                                    RouteScreen.AdminMenuScreen
                                }else {
                                    RouteScreen.UserMenuScreen
                                }

                                navController.navigate(home){
                                    popUpTo(0){
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
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
                    AdminMenuScreen(
                        logout = {
                            SharedPreferencesUtils.clearPreference(context)
                            navController.navigate(RouteScreen.HomeScreen)
                        }
                    )
                }

                composable <RouteScreen.UserMenuScreen> {
                    UserMenuScreen(
                        logout = {
                            SharedPreferencesUtils.clearPreference(context)
                            mainViewModel.usersViewModel.logout()
                            navController.navigate(RouteScreen.HomeScreen){
                                popUpTo(0){
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
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
}
