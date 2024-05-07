package com.example.cvd_monitoring.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class PatientBottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : PatientBottomBar(
        route = "HOME",
        title = "Home",
        icon = Icons.Default.Home
    )

    data object Profile : PatientBottomBar(
        route = "currentPatient/{slug}/get",
        title = "Profile",
        icon = Icons.Default.Person
    )

    data object Notifications : PatientBottomBar(
        route = "notification/{slug}/patient",
        title = "Notifications",
        icon = Icons.Default.Notifications
    )

    data object Settings : PatientBottomBar(
        route = "SETTINGS",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}

fun PatientBottomBar.getRouteWithSlug(slug: String): String {
    return route.replace("{slug}", slug)
}