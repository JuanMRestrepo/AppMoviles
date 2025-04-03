package com.unieventos.ui.clientes.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.unieventos.ui.clientes.navigation.RouteUserTab

@Composable
fun HomeBottomBar(
    navController: NavHostController
) {

    var lista = listOf<NavigationBarUser>(

        NavigationBarUser(
            icon = Icons.Outlined.Home,
            route = RouteUserTab.Home,
            title = "Inicio",
            iconSelected = Icons.Filled.Home
        ),
        NavigationBarUser(
            icon = Icons.Outlined.LocationOn,
            route = RouteUserTab.Reports,
            title = "Mis reportes",
            iconSelected = Icons.Filled.LocationOn
        ),
        NavigationBarUser(
            icon = Icons.Outlined.Notifications,
            route = RouteUserTab.Notifications,
            title = "Notificaciones",
            iconSelected = Icons.Filled.Notifications
        ),
        NavigationBarUser(
            icon = Icons.Outlined.Person,
            route = RouteUserTab.Profile,
            title = "Perfil",
            iconSelected = Icons.Filled.Person
        )

    )

    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        lista.forEach {

            val isSelected = currentDestination?.route == it.route::class.qualifiedName
            NavigationBarItem(
                label = {
                    Text(text = it.title)
                },
                onClick = {
                    navController.navigate(it.route)
                },
                icon = {
                    if(isSelected){
                        Icon(
                            imageVector = it.iconSelected,
                            contentDescription = it.title
                        )
                    }else {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.title
                        )
                    }
                },
                selected = isSelected
            )
        }
    }
}

data class NavigationBarUser(val route: RouteUserTab, val title: String, val icon: ImageVector, val iconSelected: ImageVector)