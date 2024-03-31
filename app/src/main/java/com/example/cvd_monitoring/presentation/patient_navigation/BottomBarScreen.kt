package com.example.cvd_monitoring.presentation.patient_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = "patientList",
        title = "Home",
        icon = Icons.Default.Home
    )

    data object Profile : BottomBarScreen(
        route = "SignIn",
        title = "Login",
        icon = Icons.Default.Person
    )

    data object Settings : BottomBarScreen(
        route = "signUp",
        title = "Register",
        icon = Icons.Default.Settings
    )
}