package com.example.cvd_monitoring.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bottomnavbardemo.screens.home.DoctorBottomBarScreen
import com.example.bottomnavbardemo.screens.home.PatientBottomBarScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(
            route = Graph.PATIENT_HOME,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientBottomBarScreen(
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                logout = {
                    navController.navigate(AuthScreen.Home.route) {
                        popUpTo(0){}
                    }
                }
            )
        }

        composable(
            route = Graph.DOCTOR_HOME,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorBottomBarScreen(
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                logout = {
                    navController.navigate(AuthScreen.Home.route) {
                        popUpTo(0){}
                    }
                }
            )
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val PATIENT_HOME = "CurrentPatient/{slug}/get"
    const val DOCTOR_HOME = "CurrentDoctor/{slug}/get"
    const val DETAILS = "root_graph"
}