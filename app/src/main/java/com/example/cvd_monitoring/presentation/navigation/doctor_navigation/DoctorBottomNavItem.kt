package com.example.cvd_monitoring.presentation.navigation.doctor_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DoctorBottomNavItem(val label: String, val icons: ImageVector, val route: String) {

    data object Overview : DoctorBottomNavItem("Info", Icons.Default.Info, "overview")
    data object DoctorProfile : DoctorBottomNavItem("Doctor", Icons.Default.Person, "currentDoctor/{slug}/get")
    data object Patients : DoctorBottomNavItem("Patients", Icons.Default.Face, "patientCard/{slug}")
    data object Appointments : DoctorBottomNavItem("Appointment", Icons.Default.Share, "notification/{slug}/patient")
    data object More : DoctorBottomNavItem("More", Icons.Default.MoreVert, "more")
}

fun DoctorBottomNavItem.getRouteWithSlug(slug: String): String {
    return route.replace("{slug}", slug)
}
