package com.unieventos.ui.admins.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Home
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
import com.unieventos.ui.admins.navigation.RouteAdminTab

@Composable
fun HomeBottomBarAdmin(
    navController: NavHostController
    ) {
        val selectedColor = Color(0xFFFF4B3A)
        var lista = listOf<NavigationAdminUser>(

        NavigationAdminUser(
            icon = Icons.Outlined.Home,
            route = RouteAdminTab.HomeAdmin,
            title = stringResource(id = R.string.homeIcon),
            iconSelected = Icons.Filled.Home
        ),
        NavigationAdminUser(
            icon = Icons.Outlined.Group,
            route = RouteAdminTab.UsersAdmin,
            title = stringResource(id = R.string.usersIcon),
            iconSelected = Icons.Filled.Group
        ),
        NavigationAdminUser(
            icon = Icons.Outlined.Assignment,
            route = RouteAdminTab.ReportsAdmin,
            title = stringResource(id = R.string.reportsIcon),
            iconSelected = Icons.Filled.Assignment
        ),
        NavigationAdminUser(
            icon = Icons.Outlined.Analytics,
            route = RouteAdminTab.StaticsAdmin,
            title = stringResource(id = R.string.staticsLbl),
            iconSelected = Icons.Filled.Analytics
        ),
        NavigationAdminUser(
            icon = Icons.Outlined.Person,
            route = RouteAdminTab.ProfileAdmin,
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
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    if (isSelected) {
                        Icon(
                            imageVector = it.iconSelected,
                            contentDescription = it.title,
                        )
                    } else {
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
                selected = isSelected
            )
        }
    }
}

data class NavigationAdminUser(
    val route: RouteAdminTab,
    val title: String,
    val icon: ImageVector,
    val iconSelected: ImageVector,
)

