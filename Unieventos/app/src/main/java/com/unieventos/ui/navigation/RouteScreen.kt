package com.unieventos.ui.navigation

import kotlinx.serialization.Serializable

sealed class RouteScreen {

    @Serializable
    data object HomeScreen : RouteScreen()

    @Serializable
    data object LoginScreen : RouteScreen()

    @Serializable
    data object RestartPassword1Screen : RouteScreen()

    @Serializable
    data object RestartPassword2Screen : RouteScreen()

    @Serializable
    data object SingUpScreen : RouteScreen()

    @Serializable
    data object VerificationAccountScreen : RouteScreen()

    @Serializable
    data object UserMenuScreen : RouteScreen()

    @Serializable
    data object AdminMenuScreen : RouteScreen()

    @Serializable
    data class ReportDetail(val id: String) : RouteScreen()

    @Serializable
    data object Settings : RouteScreen()

}