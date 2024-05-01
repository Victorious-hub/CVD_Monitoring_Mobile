package com.example.cvd_monitoring.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.navigation.AuthRouteScreen
import com.example.cvd_monitoring.navigation.Graph
import com.example.cvd_monitoring.presentation.auth.authentication_screen.AuthenticationScreen
import com.example.cvd_monitoring.presentation.auth.register_screen.RegistrationScreen
import com.example.cvd_monitoring.presentation.navigation.home.HomeScreen
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientBottomNavItem
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientDrawer

fun NavGraphBuilder.authNavGraph(rootNavController: NavHostController){
    navigation(
        route = Graph.AuthGraph,
        startDestination = AuthRouteScreen.Home.route
    ){
        composable(route = AuthRouteScreen.Home.route) {
            HomeScreen(navController = rootNavController)
        }
        composable(route = AuthRouteScreen.SignIn.route) {
            AuthenticationScreen(navController = rootNavController)
        }
        composable(route = AuthRouteScreen.SignUp.route) {
            RegistrationScreen(navController = rootNavController)
        }

        composable(
            route = PatientBottomNavItem.PatientProfile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientDrawer(
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }
    }
}