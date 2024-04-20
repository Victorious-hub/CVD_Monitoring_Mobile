package com.example.cvd_monitoring.presentation.navigation.patient_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class PatientBottomNavItem(val label: String, val icons: ImageVector, val route: String) {

    data object Overview : PatientBottomNavItem("Info", Icons.Default.Info, "overview")
    data object PatientProfile : PatientBottomNavItem("Profile", Icons.Default.Person, "currentPatient/{slug}/get")
    data object Card : PatientBottomNavItem("Card", Icons.Default.Favorite, "patientCard/{slug}")
    data object Notification : PatientBottomNavItem("Notification", Icons.Default.Notifications, "notification/{slug}/patient")
    data object More : PatientBottomNavItem("More", Icons.Default.MoreVert, "more")

}

fun PatientBottomNavItem.getRouteWithSlug(slug: String): String {
    return route.replace("{slug}", slug)
}
