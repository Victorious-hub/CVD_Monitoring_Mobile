package com.example.cvd_monitoring.presentation.navigation.graphs

import com.example.cvd_monitoring.presentation.doctors.patient_detail.PatientDetailScreen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.patientActionsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PATIENT_ACTIONS,
        startDestination = PatientActions.PatientProfile.route
    ) {
        composable(
            route = PatientActions.PatientProfile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientDetailScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

    }
}

sealed class PatientActions(val route: String) {
    data object PatientProfile: DataUpdateScreen(route = "updateData/{slug}/data")
    data object PatientContact : DataUpdateScreen(route = "updateContact/{slug}/contact")
    data object DoctorData : DataUpdateScreen(route = "updateDoctor/{slug}/contact")
}

fun PatientActions.getRouteWithSlug(slug: String?): String? {
    return slug?.let { route.replace("{slug}", it) }
}