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
import com.example.cvd_monitoring.presentation.doctors.appointment.AppointmentScreen
import com.example.cvd_monitoring.presentation.doctors.card_create.CardCreateScreen
import com.example.cvd_monitoring.presentation.doctors.conclusion.PatientConclusionScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_blood_create.BloodAnalysisCreateScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_cholesterol_create.CholesterolAnalysisCreateScreen
import com.example.cvd_monitoring.presentation.treatment.medications.MedicationListScreen
import com.example.cvd_monitoring.presentation.doctors.patient_card.PatientCardDetailScreen
import com.example.cvd_monitoring.presentation.treatment.prescription.PatientPrescriptionScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.doctorPatientActionsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PATIENT_ACTIONS,
        startDestination = DoctorPatientActions.PatientProfile.route
    ) {
        composable(
            route = DoctorPatientActions.PatientProfile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientDetailScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickCardDetail = { email ->
                    val route = DoctorPatientActions.PatientCard.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },

                onClickCreateCard = { email ->
                    val route = DoctorPatientActions.PatientCardCreate.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
            )
        }

        composable(
            route = DoctorPatientActions.PatientCard.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientCardDetailScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickCreateBloodAnalysis = { email ->
                    val route = DoctorPatientActions.PatientBlood.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickCreateCholesterolAnalysis = { email ->
                    val route = DoctorPatientActions.PatientCholesterol.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickCreateAppointment = { email ->
                    val route = DoctorPatientActions.PatientAppointment.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickMedicationList = { email ->
                    val route = DoctorPatientActions.Medication
                    navController.navigate(DoctorPatientActions.Medication.route)
                },
                onClickConclusion = { email ->
                    val route = DoctorPatientActions.PatientConclusion.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
            )
        }

        composable(
            route = DoctorPatientActions.PatientConclusion.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            PatientConclusionScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorPatientActions.Medication.route,

        ) {
            MedicationListScreen(
                navController

            )
        }

        composable(
            route = DoctorPatientActions.PatientPrescription.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            PatientPrescriptionScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorPatientActions.PatientCardCreate.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            CardCreateScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorPatientActions.PatientBlood.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            BloodAnalysisCreateScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorPatientActions.PatientAppointment.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            AppointmentScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorPatientActions.PatientCholesterol.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            CholesterolAnalysisCreateScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorPatientActions.PatientCardCreate.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            CardCreateScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }
    }
}

sealed class DoctorPatientActions(val route: String) {
    data object PatientProfile: DoctorPatientActions(route = "updateData/{slug}/data")
    data object PatientCard : DoctorPatientActions(route = "patientCard/{slug}")
    data object PatientBlood : DoctorPatientActions(route = "createBloodAnalysis/{slug}")
    data object PatientCholesterol : DoctorPatientActions(route = "createCholesterolAnalysis/{slug}")
    data object PatientAppointment : DoctorPatientActions(route = "patientAppointment/{slug}/create")
    data object  PatientCardCreate : DoctorPatientActions(route = "cardCreate/{slug}")
    data object  Medication : DoctorPatientActions(route = "medicationList")
    data object PatientPrescription: DoctorPatientActions(route = "patientPrescription/{slug}")
    data object PatientConclusion: DoctorPatientActions(route = "patientConclusion/{slug}")

}

fun DoctorPatientActions.getRouteWithSlug(slug: String?): String? {
    return slug?.let { route.replace("{slug}", it) }
}