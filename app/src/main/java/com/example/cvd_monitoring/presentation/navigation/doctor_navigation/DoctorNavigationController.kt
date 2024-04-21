package com.example.cvd_monitoring.presentation.navigation.doctor_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.doctors.doctor_contact_screen.DoctorContactScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_patients.DoctorPatientsScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.DoctorProfileScreen
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen
import com.example.cvd_monitoring.presentation.navigation.more.MoreScreen
import com.example.cvd_monitoring.presentation.notification.NotificationScreen
import com.example.cvd_monitoring.presentation.overview.OverviewScreen
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardScreen
import com.example.cvd_monitoring.presentation.patients.patient_contact_screen.PatientContactScreen
import com.example.cvd_monitoring.presentation.patients.patient_data_screen.PatientUpdateScreen

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
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorBottomNavItem.Patients.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorPatientsScreen(
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

        contactNavGraph(navController = navController)
    }
}


fun NavGraphBuilder.contactNavGraph(navController: NavHostController) {
    navigation(
        route = "${Screen.UpdateDoctor.route}/{slug}/contact",
        startDestination = Screen.UpdateDoctor.route
    ) {
        composable(
            route = Screen.UpdateDoctor.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorContactScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }
    }
}

