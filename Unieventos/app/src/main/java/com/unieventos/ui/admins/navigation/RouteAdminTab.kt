package com.unieventos.ui.admins.navigation

import com.unieventos.ui.clientes.navigation.RouteUserTab
import kotlinx.serialization.Serializable

sealed class RouteAdminTab {

    @Serializable
    data object HomeAdmin : RouteAdminTab()

    @Serializable
    data object UsersAdmin : RouteAdminTab()

    @Serializable
    data object ReportsAdmin : RouteAdminTab()

    @Serializable
    data object StaticsAdmin : RouteAdminTab()

    @Serializable
    data object ProfileAdmin : RouteAdminTab()

    @Serializable
    data object EditProfileAdmin : RouteAdminTab()

    @Serializable
    data object AllReports : RouteAdminTab()

    @Serializable
    data object Setting : RouteAdminTab()

    @Serializable
    data class ReportDetailAdmin(val id: String) : RouteAdminTab()

}
