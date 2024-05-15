package com.example.cvd_monitoring.presentation.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.doctors.schedule.ScheduleScreen
import com.example.cvd_monitoring.presentation.patients.patient_appointment.PatientAppointmentScreen
import com.example.cvd_monitoring.presentation.patients.patient_doctor_list.PatientDoctorListScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.patientActionsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PATIENT_ACTIONS,
        startDestination = DoctorPatientActions.PatientProfile.route
    ) {
        composable(
            route = PatientActions.DoctorList.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientDoctorListScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = PatientActions.PatientAppointment.route,
        ) {
            ScheduleScreen(
                navController,
            )
        }

        composable(
            route = PatientActions.PatientAppointmentCreate.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) {backstackEntry ->
            PatientAppointmentScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

    }
}

sealed class PatientActions(val route: String) {
    data object DoctorList: PatientActions(route = "doctorList/{slug}")
    data object PatientAppointment : PatientActions(route = "scheduleList")
    data object PatientAppointmentCreate : PatientActions(route = "patientAppointment/{slug}")
}

fun PatientActions.getRouteWithSlug(slug: String?): String? {
    return slug?.let { route.replace("{slug}", it) }
}