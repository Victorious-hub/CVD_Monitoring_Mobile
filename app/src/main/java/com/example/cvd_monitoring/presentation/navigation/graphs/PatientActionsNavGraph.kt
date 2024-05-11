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
import com.example.cvd_monitoring.domain.model.patients.PatientCard
import com.example.cvd_monitoring.presentation.doctors.appointment.AppointmentScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_blood_create.BloodAnalysisCreateScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_cholesterol_create.CholesterolAnalysisCreateScreen
import com.example.cvd_monitoring.presentation.doctors.patient_card.PatientCardDetailScreen
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardScreen

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
                onClickCardDetail = { email ->
                    val route = PatientActions.PatientCard.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
            )
        }

        composable(
            route = PatientActions.PatientCard.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientCardDetailScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickCreateBloodAnalysis = { email ->
                    val route = PatientActions.PatientBlood.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickCreateCholesterolAnalysis = { email ->
                    val route = PatientActions.PatientCholesterol.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickCreateAppointment = { email ->
                    val route = PatientActions.PatientAppointment.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
            )
        }

        composable(
            route = PatientActions.PatientBlood.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            BloodAnalysisCreateScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = PatientActions.PatientAppointment.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            AppointmentScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = PatientActions.PatientCholesterol.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            CholesterolAnalysisCreateScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = PatientActions.PatientProfile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientDetailScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickCardDetail = { email ->
                    val route = PatientActions.PatientCard.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
            )
        }

    }
}

sealed class PatientActions(val route: String) {
    data object PatientProfile: DataUpdateScreen(route = "updateData/{slug}/data")
    data object PatientCard : DataUpdateScreen(route = "patientCard/{slug}")
    data object PatientBlood : DataUpdateScreen(route = "createBloodAnalysis/{slug}")
    data object PatientCholesterol : DataUpdateScreen(route = "createCholesterolAnalysis/{slug}")
    data object PatientAppointment : DataUpdateScreen(route = "patientAppointment/{slug}/create")
}

fun PatientActions.getRouteWithSlug(slug: String?): String? {
    return slug?.let { route.replace("{slug}", it) }
}