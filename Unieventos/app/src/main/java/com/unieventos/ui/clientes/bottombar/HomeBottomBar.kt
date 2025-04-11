package com.unieventos.ui.clientes.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.unieventos.R
import com.unieventos.ui.clientes.navigation.RouteUserTab

@Composable
fun HomeBottomBar(
    navController: NavHostController
) {
    val selectedColor = Color(0xFFFF4B3A)
    var lista = listOf<NavigationBarUser>(
        NavigationBarUser(
            icon = Icons.Outlined.Home,
            route = RouteUserTab.Home,
            title = stringResource(id = R.string.homeIcon),
            iconSelected = Icons.Filled.Home
        ),
        NavigationBarUser(
            icon = Icons.Outlined.List,
            route = RouteUserTab.Reports,
            title = stringResource(id = R.string.listIcon),
            iconSelected = Icons.Filled.List
        ),
        NavigationBarUser(
            icon = Icons.Outlined.AddCircle,
            route = RouteUserTab.CreateReport,
            title = "",
            iconSelected = Icons.Filled.AddCircle,
            showLabel = false,
            isCenterItem = true
        ),
        NavigationBarUser(
            icon = Icons.Outlined.Notifications,
            route = RouteUserTab.Notifications,
            title = stringResource(id = R.string.alertIcon),
            iconSelected = Icons.Filled.Notifications
        ),
        NavigationBarUser(
            icon = Icons.Outlined.Person,
            route = RouteUserTab.Profile,
            title = stringResource(id = R.string.youIcon),
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
                    navController.navigate(it.route){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    if(isSelected){
                        Icon(
                            imageVector = it.iconSelected,
                            contentDescription = it.title,
                            )
                    }else {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.title,
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedColor,
                    selectedTextColor = selectedColor,
                ),
                selected = isSelected,
                alwaysShowLabel = it.showLabel
            )
        }
    }
}

data class NavigationBarUser(
    val route: RouteUserTab,
    val title: String,
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val showLabel: Boolean = true,
    val isCenterItem: Boolean = false
)