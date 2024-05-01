package com.example.cvd_monitoring.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cvd_monitoring.navigation.AuthRouteScreen
import com.example.cvd_monitoring.navigation.Graph
import com.example.cvd_monitoring.navigation.PatientBottomNavItem
import com.example.cvd_monitoring.presentation.navigation.home.HomeScreen
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen

@Composable
fun PatientNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController
){
    NavHost(
        navController = homeNavController,
        route = Graph.PatientScreenGraph,
        startDestination = PatientBottomNavItem.PatientProfile.route
    ) {
        composable(
            route = PatientBottomNavItem.PatientProfile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientProfileScreen(
                rootNavController,
                slug = backstackEntry.arguments?.getString("slug") ?: ""
            )
        }
    }
}