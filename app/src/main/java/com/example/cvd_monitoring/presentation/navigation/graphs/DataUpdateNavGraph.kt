package com.example.cvd_monitoring.presentation.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.doctors.doctor_contact_screen.DoctorContactScreen
import com.example.cvd_monitoring.presentation.patients.patient_contact_screen.PatientContactScreen
import com.example.cvd_monitoring.presentation.patients.patient_data_screen.PatientUpdateScreen
import com.example.cvd_monitoring.presentation.treatment.blood_analysis.BloodAnalysisScreen
import com.example.cvd_monitoring.presentation.treatment.cholesterol_analysis.CholesterolAnalysisScreen
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.PatientPrescriptionListScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.updateDataNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.UPDATE_DATA,
        startDestination = DataUpdateScreen.PatientData.route
    ) {
        composable(
            route = DataUpdateScreen.PatientData.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientUpdateScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DataUpdateScreen.PatientContact.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientContactScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DataUpdateScreen.DoctorData.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorContactScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

    }
}

sealed class DataUpdateScreen(val route: String) {
    data object PatientData : DataUpdateScreen(route = "updateData/{slug}/data")
    data object PatientContact : DataUpdateScreen(route = "updateContact/{slug}/contact")
    data object DoctorData : DataUpdateScreen(route = "updateDoctor/{slug}/contact")
}

fun DataUpdateScreen.getRouteWithSlug(slug: String?): String? {
    return slug?.let { route.replace("{slug}", it) }
}