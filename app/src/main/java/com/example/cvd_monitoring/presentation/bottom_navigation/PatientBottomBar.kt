package com.example.cvd_monitoring.presentation.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class PatientBottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : PatientBottomBar(
        route = "currentPatient/{slug}/get",
        title = "HOME",
        icon = Icons.Default.Home
    )

    data object Profile : PatientBottomBar(
        route = "PROFILE",
        title = "PROFILE",
        icon = Icons.Default.Person
    )

    data object Settings : PatientBottomBar(
        route = "SETTINGS",
        title = "SETTINGS",
        icon = Icons.Default.Settings
    )
}

fun PatientBottomBar.getRouteWithSlug(slug: String): String {
    return route.replace("{slug}", slug)
}