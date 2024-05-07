package com.example.cvd_monitoring.presentation.navigation.graphs.doctor_graph


import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.auth.logout_screen.LogoutScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_patients.DoctorPatientsScreen
import com.example.cvd_monitoring.presentation.navigation.DoctorBottomBar
import com.example.cvd_monitoring.presentation.navigation.graphs.Graph
import com.example.cvd_monitoring.presentation.navigation.screens.ScreenContent
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.DoctorProfileScreen
import com.example.cvd_monitoring.presentation.navigation.PatientBottomBar

@Composable
fun DoctorHomeNavGraph(
    navController: NavHostController,
    logout: () -> Unit
) {
    NavHost(
        navController = navController,
        route = Graph.DOCTOR_HOME,
        startDestination = DoctorBottomBar.Home.route
    ) {

        composable(route = DoctorBottomBar.Home.route) {
            ScreenContent(name = DoctorBottomBar.Home.route) {
                navController.popBackStack(
                    route = DoctorBottomBar.Home.route,
                    inclusive = false
                )
            }
        }

        composable(
            route = DoctorBottomBar.Profile.route,
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

        composable(
            route = DoctorBottomBar.Patients.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorPatientsScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClick = {navController.navigate(Graph.DETAILS) }
            )
        }

        composable(route = PatientBottomBar.Settings.route) {
            LogoutScreen(navHostController = navController, logout = logout)
        }

        detailsNavGraph(
            navController = navController
        )
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Overview.route
    ) {
//        composable(route = AuthScreen.Login.route) {
//            AuthenticationScreen(navController)
//        }

//        composable(
//            route = DetailsScreen.Overview.route,
//            arguments = listOf(navArgument("slug") { type = NavType.StringType })
//        ) { backstackEntry ->
//            PatientDetailScreen(
//                navController,
//                slug = backstackEntry.arguments?.getString("slug") ?: "",
//            )
//        }
    }
}

sealed class DetailsScreen(val route: String) {
    data object Information : DetailsScreen(route = "INFORMATION")
    data object Overview : DetailsScreen(route = "currentPatient/{slug}/get")
}