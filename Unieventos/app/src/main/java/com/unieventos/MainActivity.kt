package com.unieventos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.unieventos.ui.navigation.Navigation
import com.unieventos.ui.navigation.RouteScreen
import com.unieventos.ui.screens.HomeScreen
import com.unieventos.ui.screens.LoginScreen
import com.unieventos.ui.screens.SingUpScreen
import com.unieventos.ui.screens.PruebaScreen
import com.unieventos.ui.screens.RestartPassword1
import com.unieventos.ui.screens.VerificationAccountScreen
import com.unieventos.ui.screens.SingUpScreen
import com.unieventos.ui.theme.UnieventosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnieventosTheme {
                UnieventosTheme {
                    Navigation()
                }
            }

        }
    }
}
