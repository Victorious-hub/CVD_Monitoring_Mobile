package com.example.cvd_monitoring.presentation.navigation.patient_navigation

import android.annotation.SuppressLint
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cvd_monitoring.presentation.navigation.NavigationController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CurrentUserScreen(
    slug: String
) {
    val navController = rememberNavController()
    val items = listOf(
        PatientBottomNavItem.Overview,
        PatientBottomNavItem.PatientProfile,
        PatientBottomNavItem.Card,
        PatientBottomNavItem.Notification,
        PatientBottomNavItem.More,
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = androidx.compose.material.MaterialTheme.colors.background) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach {
                    BottomNavigationItem(selected = currentRoute == it.route,
                        label = {
                            Text(
                                text = it.label,
                                color = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = it.icons, contentDescription = null,
                                tint = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )

                        },

                        onClick = {
                            if (currentRoute != it.route) {
                                val newRoute = when (it) {
                                    PatientBottomNavItem.Overview,
                                    PatientBottomNavItem.PatientProfile -> it.getRouteWithSlug(slug)
                                    PatientBottomNavItem.Card -> it.getRouteWithSlug(slug)
                                    PatientBottomNavItem.Notification -> it.getRouteWithSlug(slug)
                                    PatientBottomNavItem.More -> it.route
                                }
                                navController.navigate(newRoute) {
                                    launchSingleTop = true
                                }
                            }
                        })

                }


            }


        }) {
        NavigationController(navController = navController)
    }
}
