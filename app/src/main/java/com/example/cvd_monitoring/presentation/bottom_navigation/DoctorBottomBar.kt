package com.example.cvd_monitoring.presentation.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DoctorBottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : DoctorBottomBar(
        route = "currentDoctor/{slug}/get",
        title = "HOME",
        icon = Icons.Default.Home
    )

    data object Profile : DoctorBottomBar(
        route = "PROFILE",
        title = "PROFILE",
        icon = Icons.Default.Person
    )

    data object Settings : DoctorBottomBar(
        route = "SETTINGS",
        title = "SETTINGS",
        icon = Icons.Default.Settings
    )
}

fun DoctorBottomBar.getRouteWithSlug(slug: String): String {
    return route.replace("{slug}", slug)
}