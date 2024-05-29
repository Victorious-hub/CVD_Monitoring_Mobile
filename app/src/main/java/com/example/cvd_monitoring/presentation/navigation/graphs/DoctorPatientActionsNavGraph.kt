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
import com.example.cvd_monitoring.presentation.doctors.card_update.UpdateCardScreen
import com.example.cvd_monitoring.presentation.doctors.change_blood.ChangeBloodAnalysisScreen
import com.example.cvd_monitoring.presentation.doctors.change_cholesterol.ChangeCholesterolAnalysisScreen
import com.example.cvd_monitoring.presentation.doctors.check_blood.CheckPatientBloodScreen
import com.example.cvd_monitoring.presentation.doctors.check_cholesterol.CheckPatientCholesterolScreen
import com.example.cvd_monitoring.presentation.doctors.conclusion.PatientConclusionScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_appointment.DoctorAppointmentScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_blood_create.BloodAnalysisCreateScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_cholesterol_create.CholesterolAnalysisCreateScreen
import com.example.cvd_monitoring.presentation.doctors.patient_card.PatientCardDetailScreen
import com.example.cvd_monitoring.presentation.treatment.medications.MedicationListScreen
import com.example.cvd_monitoring.presentation.doctors.patient_card.PatientCardInfoScreen
import com.example.cvd_monitoring.presentation.navigation.DoctorBottomBar
import com.example.cvd_monitoring.presentation.treatment.doctor_decline_prescription.PrescriptionDeclineScreen
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
                    val route = DoctorPatientActions.PatientInfoCard.getRouteWithSlug(email)
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
                onClickBackToMain = {
                    navController.navigate(DoctorBottomBar.Home.route)
                },
            )
        }

        composable(
            route = DoctorPatientActions.PatientInfoCard.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientCardInfoScreen(
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
                onClickMedicationList = {
                    navController.navigate(DoctorPatientActions.Medication.route)
                },
                onClickConclusion = { email ->
                    val route = DoctorPatientActions.PatientConclusion.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickCheckPatientBlood = {slug ->
                    val route = DoctorPatientActions.PatientBloodAnalysis.getRouteWithSlug(slug)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickCheckPatientCholesterol = {slug ->
                    val route = DoctorPatientActions.PatientCholesterolAnalysis.getRouteWithSlug(slug)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickCheckPatientPrescriptions = {email ->
                    val route = DoctorPatientActions.PatientPrescriptions.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickCheckPatientDetail = {email ->
                    val route = DoctorPatientActions.PatientCardDetail.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickBackToMain = {
                    navController.navigate(DoctorBottomBar.Home.route)
                },
                onDismiss = {}
            )
        }

        composable(
            route = DoctorPatientActions.PatientCardUpdate.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            UpdateCardScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorPatientActions.PatientCardDetail.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType})
        ) { backstackEntry ->
            PatientCardDetailScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
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

        composable(
            route = DoctorPatientActions.DoctorAppointments.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorAppointmentScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickBackToMain = {
                    navController.navigate(DoctorBottomBar.Home.route)
                }
            )
        }

        composable(
            route = DoctorPatientActions.PatientBloodAnalysis.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            CheckPatientBloodScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickUpdateBlood = {slug ->
                    val route = DoctorPatientActions.UpdatePatientBloodAnalysis.getRouteWithSlug(slug)
                    if (route != null) {
                        navController.navigate(route)
                    }
                }
            )
        }

        composable(
            route = DoctorPatientActions.PatientCholesterolAnalysis.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            CheckPatientCholesterolScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickUpdateCholesterol = {slug ->
                    val route = DoctorPatientActions.UpdatePatientCholesterolAnalysis.getRouteWithSlug(slug)
                    if (route != null) {
                        navController.navigate(route)
                    }
                }
            )
        }

        composable(
            route = DoctorPatientActions.UpdatePatientBloodAnalysis.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            ChangeBloodAnalysisScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorPatientActions.UpdatePatientCholesterolAnalysis.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            ChangeCholesterolAnalysisScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = DoctorPatientActions.PatientPrescriptions.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PrescriptionDeclineScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onClickBackToMain = {
                    navController.navigate(DoctorBottomBar.Home.route)
                }
            )
        }

    }
}

sealed class DoctorPatientActions(val route: String) {
    data object PatientProfile: DoctorPatientActions(route = "updateData/{slug}/data")
    data object PatientInfoCard : DoctorPatientActions(route = "patientCard/{slug}")
    data object PatientBlood : DoctorPatientActions(route = "createBloodAnalysis/{slug}")
    data object PatientCholesterol : DoctorPatientActions(route = "createCholesterolAnalysis/{slug}")
    data object PatientAppointment : DoctorPatientActions(route = "patientAppointment/{slug}/create")
    data object  PatientCardCreate : DoctorPatientActions(route = "cardCreate/{slug}")
    data object  Medication : DoctorPatientActions(route = "medicationList")
    data object PatientPrescription: DoctorPatientActions(route = "patientPrescription/{slug}")
    data object PatientConclusion: DoctorPatientActions(route = "patientConclusion/{slug}")
    data object DoctorAppointments: DoctorPatientActions(route = "doctorAppointments/{slug}")
    data object PatientBloodAnalysis: DoctorPatientActions(route = "patientBlood/{slug}/get/last")
    data object PatientCholesterolAnalysis: DoctorPatientActions(route = "patientCholesterol/{slug}/get/last")
    data object UpdatePatientBloodAnalysis: DoctorPatientActions(route = "updateBlood/{slug}/update")
    data object UpdatePatientCholesterolAnalysis: DoctorPatientActions(route = "updateCholesterol/{slug}/update")
    data object PatientPrescriptions: DoctorPatientActions(route = "patientPrescription/{slug}/get")
    data object PatientCardDetail: DoctorPatientActions(route = "patientCard/{slug}/get")
    data object PatientCardUpdate: DoctorPatientActions(route = "patientCard/{slug}/update")
}

fun DoctorPatientActions.getRouteWithSlug(slug: String?): String? {
    return slug?.let { route.replace("{slug}", it) }
}