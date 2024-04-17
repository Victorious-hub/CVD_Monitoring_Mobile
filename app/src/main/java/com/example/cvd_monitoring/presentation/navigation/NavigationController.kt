package com.example.cvd_monitoring.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen
import com.example.cvd_monitoring.presentation.more.MoreScreen
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientBottomNavItem
import com.example.cvd_monitoring.presentation.notification.NotificationScreen
import com.example.cvd_monitoring.presentation.overview.OverviewScreen
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardScreen

@Composable
fun NavigationController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = PatientBottomNavItem.Overview.route) {
        composable(
            route = PatientBottomNavItem.Overview.route,
        ){
            OverviewScreen(navController)
        }

        composable(
            route = PatientBottomNavItem.PatientProfile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientProfileScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = PatientBottomNavItem.Card.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientCardScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = PatientBottomNavItem.Notification.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            NotificationScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = PatientBottomNavItem.More.route,
        ) {
            MoreScreen(navController)
        }
    }

}


