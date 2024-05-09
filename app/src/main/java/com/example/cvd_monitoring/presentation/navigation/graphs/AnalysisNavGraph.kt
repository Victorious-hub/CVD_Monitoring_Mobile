package com.example.cvd_monitoring.presentation.navigation.graphs


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

import com.example.cvd_monitoring.presentation.treatment.blood_analysis.BloodAnalysisScreen
import com.example.cvd_monitoring.presentation.treatment.cholesterol_analysis.CholesterolAnalysisScreen
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.PatientPrescriptionListScreen

fun NavGraphBuilder.analysisNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ANALYSIS,
        startDestination = AnalysisScreen.Blood.route
    ) {
        composable(
            route = AnalysisScreen.Blood.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            BloodAnalysisScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = AnalysisScreen.Cholesterol.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            CholesterolAnalysisScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = AnalysisScreen.Prescription.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientPrescriptionListScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

    }
}

sealed class AnalysisScreen(val route: String) {
    data object Blood : AnalysisScreen(route = "patientBlood/{slug}/get")
    data object Cholesterol : AnalysisScreen(route = "patientCholesterol/{slug}/get")
    data object Prescription : AnalysisScreen(route = "patientPrescription/{slug}/get")
}

fun AnalysisScreen.getRouteWithSlug(slug: String?): String? {
    return slug?.let { route.replace("{slug}", it) }
}