package com.unieventos.ui.clientes.navigation

import kotlinx.serialization.Serializable

sealed class RouteUserTab {

    @Serializable
    data object Home : RouteUserTab()

    @Serializable
    data object Reports : RouteUserTab()

    @Serializable
    data object CreateReport : RouteUserTab()

    @Serializable
    data object Notifications : RouteUserTab()

    @Serializable
    data object Profile : RouteUserTab()

    @Serializable
    data object EditProfile : RouteUserTab()

    @Serializable
    data object SavedItems : RouteUserTab()

    @Serializable
    data object YourActivity : RouteUserTab()
}