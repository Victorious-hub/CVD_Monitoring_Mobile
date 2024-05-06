package com.example.cvd_monitoring.presentation.bottom_navigation.graphs.patient_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.auth.authentication_screen.AuthenticationScreen
import com.example.cvd_monitoring.presentation.bottom_navigation.PatientBottomBar
import com.example.cvd_monitoring.presentation.bottom_navigation.graphs.AuthScreen
import com.example.cvd_monitoring.presentation.bottom_navigation.graphs.Graph
import com.example.cvd_monitoring.presentation.bottom_navigation.screens.ScreenContent
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen

@Composable
fun PatientHomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.PATIENT_HOME,
        startDestination = PatientBottomBar.Home.route
    ) {


        composable(
            route = PatientBottomBar.Home.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientProfileScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(route = PatientBottomBar.Settings.route) {
            ScreenContent(
                name = PatientBottomBar.Settings.route,
                onClick = {navController.navigate(Graph.DETAILS) }
            )
        }
        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ROOT,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            AuthenticationScreen(navController)
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