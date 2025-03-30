package com.unieventos.ui.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unieventos.ui.screens.HomeScreen
import com.unieventos.ui.screens.LoginScreen
import com.unieventos.ui.screens.SingUpScreen

@Composable
fun Navigation(){

    val navController = rememberNavController()

    Surface {
        NavHost(
            navController = navController,
            startDestination = RouteScreen.HomeScreen
        ) {
            composable <RouteScreen.HomeScreen> {
                HomeScreen(
                    navigateToLogIn = {
                        navController.navigate(RouteScreen.LoginScreen)
                    }
                )
            }
            composable <RouteScreen.LoginScreen> {
                LoginScreen()
            }
            composable <RouteScreen.SingUpScreen> {
                SingUpScreen()
            }
        }
    }
}