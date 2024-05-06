package com.example.cvd_monitoring.presentation.bottom_navigation.graphs.doctor_graph


import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.auth.authentication_screen.AuthenticationScreen
import com.example.cvd_monitoring.presentation.bottom_navigation.DoctorBottomBar
import com.example.cvd_monitoring.presentation.bottom_navigation.graphs.AuthScreen
import com.example.cvd_monitoring.presentation.bottom_navigation.graphs.Graph
import com.example.cvd_monitoring.presentation.bottom_navigation.screens.ScreenContent
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.DoctorProfileScreen
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen

@Composable
fun DoctorHomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.DOCTOR_HOME,
        startDestination = DoctorBottomBar.Home.route
    ) {


        composable(
            route = DoctorBottomBar.Home.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorProfileScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(route = DoctorBottomBar.Settings.route) {
            ScreenContent(
                name = DoctorBottomBar.Settings.route,
                onClick = {navController.navigate(Graph.DETAILS) }
            )
        }
        detailsNavGraph(
            navController = navController
        )
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