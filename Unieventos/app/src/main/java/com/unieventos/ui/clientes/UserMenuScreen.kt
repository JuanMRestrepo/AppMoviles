package com.unieventos.ui.clientes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.unieventos.model.Location
import com.unieventos.model.Report
import com.unieventos.model.ReportState
import com.unieventos.ui.clientes.bottombar.HomeBottomBar
import com.unieventos.ui.clientes.navigation.UserNavigation
import java.time.LocalDateTime

@Composable
fun UserMenuScreen(
    navigateToDetail: (String) -> Unit
) {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            HomeTopBar()
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
            navigateToDetail = navigateToDetail
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            Text(text = "UniEventosApp")
        }
    )
}

