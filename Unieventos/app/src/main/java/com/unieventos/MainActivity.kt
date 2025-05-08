package com.unieventos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.unieventos.ui.navigation.Navigation
import com.unieventos.ui.screens.SettingsTab
import com.unieventos.ui.theme.UnieventosTheme
import com.unieventos.viewmodel.UsersViewModel

class MainActivity : ComponentActivity() {

    private val usersViewModel:UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            UnieventosTheme {
                UnieventosTheme {
                    Navigation(
                        usersViewModel = usersViewModel
                    )
                }
            }



            //SettingsTab()
        }
    }
}
