package com.example.cvd_monitoring.presentation.navigation.graphs.patient_graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cvd_monitoring.presentation.navigation.PatientBottomBar
import com.example.cvd_monitoring.presentation.navigation.graphs.Graph
import com.example.cvd_monitoring.presentation.auth.logout_screen.LogoutScreen
import com.example.cvd_monitoring.presentation.navigation.graphs.AnalysisScreen
import com.example.cvd_monitoring.presentation.navigation.graphs.DataUpdateScreen
import com.example.cvd_monitoring.presentation.navigation.graphs.analysisNavGraph
import com.example.cvd_monitoring.presentation.navigation.graphs.getRouteWithSlug
import com.example.cvd_monitoring.presentation.navigation.graphs.patientActionsNavGraph
import com.example.cvd_monitoring.presentation.navigation.graphs.updateDataNavGraph
import com.example.cvd_monitoring.presentation.notification.NotificationScreen
import com.example.cvd_monitoring.presentation.overview.UserHomeScreen
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardScreen
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
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
            UserHomeScreen(
                navController,
            )
        }

        composable(
            route = PatientBottomBar.Profile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientProfileScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onUpdateData = {
                    DataUpdateScreen.PatientData.getRouteWithSlug(backstackEntry.arguments?.getString("slug"))
                        ?.let { it1 -> navController.navigate(it1) }
                },
                onUpdateContact = {
                    DataUpdateScreen.PatientContact.getRouteWithSlug(backstackEntry.arguments?.getString("slug"))
                        ?.let { it1 -> navController.navigate(it1) }
                },
            )
        }

        composable(
            route = PatientBottomBar.Card.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientCardScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickBlood = {
                    AnalysisScreen.Blood.getRouteWithSlug(backstackEntry.arguments?.getString("slug"))
                    ?.let { it1 -> navController.navigate(it1) }
                },
                onClickCholesterol = {
                    AnalysisScreen.Cholesterol.getRouteWithSlug(backstackEntry.arguments?.getString("slug"))
                        ?.let { it1 -> navController.navigate(it1) }
                },
                onClickPrescription = {
                    AnalysisScreen.Prescription.getRouteWithSlug(backstackEntry.arguments?.getString("slug"))
                        ?.let { it1 -> navController.navigate(it1) }
                },
                onClickConclusion = {
                    AnalysisScreen.Conclusion.getRouteWithSlug(backstackEntry.arguments?.getString("slug"))
                        ?.let { it1 -> navController.navigate(it1) }
                },
                onClickBackToMain = {
                    navController.navigate(PatientBottomBar.Home.route)
                }
            )
        }

        composable(
            route = PatientBottomBar.Notifications.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            NotificationScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickBackToMain = {
                    navController.navigate(PatientBottomBar.Home.route)
                }
            )
        }

        composable(route = PatientBottomBar.Settings.route) {
            LogoutScreen(navHostController = navController, logout = logout)
        }

        analysisNavGraph(navController = navController)
        updateDataNavGraph(navController = navController)
        patientActionsNavGraph(navController = navController)
    }
}
