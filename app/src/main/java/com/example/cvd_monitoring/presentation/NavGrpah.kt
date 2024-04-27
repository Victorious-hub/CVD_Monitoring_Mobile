package com.example.cvd_monitoring.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cvd_monitoring.presentation.auth.authentication_screen.AuthenticationScreen
import com.example.cvd_monitoring.presentation.navigation.home.HomeScreen
import com.example.cvd_monitoring.presentation.auth.register_screen.RegistrationScreen
import com.example.cvd_monitoring.presentation.check_user.CheckUserScreen
import androidx.navigation.compose.navigation
import com.example.cvd_monitoring.presentation.navigation.doctor_navigation.DoctorNavigation
import com.example.cvd_monitoring.presentation.navigation.more.MoreScreen
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientBottomNavItem
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientNavigation
import com.example.cvd_monitoring.presentation.notification.NotificationScreen
import com.example.cvd_monitoring.presentation.overview.OverviewScreen
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardScreen
import com.example.cvd_monitoring.presentation.patients.patient_data_screen.PatientUpdateScreen
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen


@SuppressLint("SuspiciousIndentation")
@Composable
fun NavGraph() {
    val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.CheckUser.route
        ) {
            navigation(
                route = Screen.CheckUser.route,
                startDestination = Screen.Home.route
            ) {

                composable(route = Screen.Home.route) {
                    HomeScreen(navController)
                }
                composable(route = Screen.SignIn.route) {
                    AuthenticationScreen(navController)
                }
                composable(route = Screen.SignUp.route) {
                    RegistrationScreen(navController)
                }


            }


            navigation(
                route = Screen.SignIn.route,
                startDestination = PatientBottomNavItem.PatientProfile.route
            ) {
                composable(
                    route = PatientBottomNavItem.PatientProfile.route,
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backstackEntry ->
                    PatientNavigation(
                        slug = backstackEntry.arguments?.getString("slug") ?: "",
                    )
                }

                composable(
                    route = PatientBottomNavItem.PatientProfile.route,
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backstackEntry ->
                    PatientProfileScreen(
                        navController,
                        slug = backstackEntry.arguments?.getString("slug") ?: ""
                    )
                }
//
//                composable(
//                    route = PatientBottomNavItem.Card.route,
//                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
//                ) { backstackEntry ->
//                    PatientCardScreen(
//                        navController,
//                        slug = backstackEntry.arguments?.getString("slug") ?: "",
//                    )
//                }
//
//                composable(
//                    route = PatientBottomNavItem.Notification.route,
//                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
//                ) { backstackEntry ->
//                    NotificationScreen(
//                        navController,
//                        slug = backstackEntry.arguments?.getString("slug") ?: "",
//                    )
//                }
//
//                composable(
//                    route = PatientBottomNavItem.More.route,
//                ) {
//                    MoreScreen(navController)
//                }

            }
        }
}