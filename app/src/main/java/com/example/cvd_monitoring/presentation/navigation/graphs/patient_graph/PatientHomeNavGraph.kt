package com.example.cvd_monitoring.presentation.navigation.graphs.patient_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.check_user.CheckUserScreen
import com.example.cvd_monitoring.presentation.navigation.PatientBottomBar
import com.example.cvd_monitoring.presentation.navigation.graphs.Graph
import com.example.cvd_monitoring.presentation.auth.logout_screen.LogoutScreen
import com.example.cvd_monitoring.presentation.navigation.screens.ScreenContent
import com.example.cvd_monitoring.presentation.notification.NotificationScreen
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen

@Composable
fun PatientHomeNavGraph(
    navController: NavHostController,
    logout: () -> Unit
) {
    NavHost(
        navController = navController,
        route = Graph.PATIENT_HOME,
        startDestination = PatientBottomBar.Home.route
    ) {


        composable(route = PatientBottomBar.Home.route) {
            ScreenContent(name = PatientBottomBar.Home.route) {
                navController.popBackStack(
                    route = PatientBottomBar.Home.route,
                    inclusive = false
                )
            }
        }

        composable(
            route = PatientBottomBar.Profile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientProfileScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = PatientBottomBar.Notifications.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            NotificationScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(route = PatientBottomBar.Settings.route) {
            LogoutScreen(navHostController = navController, logout = logout)
        }

        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ROOT,
        startDestination = Screen.CheckUser.route
    ) {
        composable(route = Screen.CheckUser.route) {
            CheckUserScreen(navController)
        }


        composable(route = DetailsScreen.Overview.route) {
            ScreenContent(name = DetailsScreen.Overview.route) {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    data object Information : DetailsScreen(route = "INFORMATION")
    data object Overview : DetailsScreen(route = "OVERVIEW")
}