package com.example.cvd_monitoring.presentation.navigation.doctor_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.DoctorProfileScreen
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen
import com.example.cvd_monitoring.presentation.navigation.more.MoreScreen
import com.example.cvd_monitoring.presentation.notification.NotificationScreen
import com.example.cvd_monitoring.presentation.overview.OverviewScreen
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardScreen

@Composable
fun DoctorNavigationController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DoctorBottomNavItem.Overview.route) {
        composable(
            route = DoctorBottomNavItem.Overview.route,
        ){
            OverviewScreen(navController)
        }

        composable(
            route = DoctorBottomNavItem.DoctorProfile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorProfileScreen(
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorBottomNavItem.Patients.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientCardScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorBottomNavItem.Appointments.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            NotificationScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorBottomNavItem.More.route,
        ) {
            MoreScreen(navController)
        }
    }

}


