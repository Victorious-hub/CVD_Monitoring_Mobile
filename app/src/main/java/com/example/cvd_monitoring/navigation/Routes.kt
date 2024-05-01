package com.example.cvd_monitoring.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

object Graph{
    const val RootGraph = "rootGraph"
    const val AuthGraph = "authGraph"
    const val PatientScreenGraph = "mainScreenGraph"
    const val NotificationGraph = "notificationGraph"
    const val SettingGraph = "settingGraph"
}


sealed class AuthRouteScreen(val route: String){
    data object SignIn : AuthRouteScreen("SignIn")
    data object Home : AuthRouteScreen("home")
    data object SignUp : AuthRouteScreen("signUp")
}

sealed class SettingRouteScreen(val route: String){
    data object SettingDetail : SettingRouteScreen("settingDetail")
}

sealed class NotificationRouteScreen(val route: String){
    data object NotificationDetail : NotificationRouteScreen("notificationDetail")
}


sealed class PatientBottomNavItem(val label: String, val icons: ImageVector, val route: String) {
    data object PatientProfile : PatientBottomNavItem("Profile", Icons.Default.Person, "currentPatient/{slug}/get")
    data object Card : PatientBottomNavItem("Card", Icons.Default.Favorite, "patientCard/{slug}")
    data object Notification : PatientBottomNavItem("Notification", Icons.Default.Notifications, "notification/{slug}/patient")
    data object More : PatientBottomNavItem("More", Icons.Default.MoreVert, "more")

}

fun PatientBottomNavItem.getRouteWithSlug(slug: String): String {
    return route.replace("{slug}", slug)
}
