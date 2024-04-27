package com.example.cvd_monitoring.presentation.navigation.patient_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.auth.authentication_screen.AuthenticationScreen
import com.example.cvd_monitoring.presentation.navigation.home.HomeScreen

import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen
import com.example.cvd_monitoring.presentation.navigation.more.MoreScreen
import com.example.cvd_monitoring.presentation.notification.NotificationScreen
import com.example.cvd_monitoring.presentation.overview.OverviewScreen
import com.example.cvd_monitoring.presentation.patients.blood_analysis.BloodAnalysisScreen
import com.example.cvd_monitoring.presentation.patients.cholesterol_analysis.CholesterolAnalysisScreen
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardScreen
import com.example.cvd_monitoring.presentation.patients.patient_contact_screen.PatientContactScreen
import com.example.cvd_monitoring.presentation.patients.patient_data_screen.PatientUpdateScreen
import com.example.cvd_monitoring.presentation.treatment.patient_prescription.PatientPrescriptionListScreen

@Composable
fun PatientNavigationController(navController: NavHostController) {

    NavHost(navController = navController, startDestination = PatientBottomNavItem.PatientProfile.route) {
        dataNavGraph(navController = navController)
        contactNavGraph(navController = navController)
        bloodNavGraph(navController = navController)
        cholesterolNavGraph(navController = navController)
        prescriptionNavGraph(navController = navController)
        //mainNavGraph(navController = navController)
    }
}


fun NavGraphBuilder.dataNavGraph(navController: NavHostController) {
    navigation(
        route = "${Screen.UpdateDataPatient.route}/{slug}/data",
        startDestination = Screen.UpdateDataPatient.route
    ) {
        composable(
            route = Screen.UpdateDataPatient.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientUpdateScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }
    }
}

fun NavGraphBuilder.prescriptionNavGraph(navController: NavHostController) {
    navigation(
        route = "${Screen.PatientPrescriptionList.route}/{slug}/get",
        startDestination = Screen.PatientPrescriptionList.route
    ) {
        composable(
            route = Screen.PatientPrescriptionList.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientPrescriptionListScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }
    }
}

fun NavGraphBuilder.cholesterolNavGraph(navController: NavHostController) {
    navigation(
        route = "${Screen.PatientCholesterolList.route}/{slug}/get",
        startDestination = Screen.PatientCholesterolList.route
    ) {
        composable(
            route = Screen.PatientCholesterolList.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            CholesterolAnalysisScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }
    }
}

fun NavGraphBuilder.bloodNavGraph(navController: NavHostController) {
    navigation(
        route = "${Screen.PatientBloodList.route}/{slug}/get",
        startDestination = Screen.PatientBloodList.route
    ) {
        composable(
            route = Screen.PatientBloodList.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            BloodAnalysisScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }
    }
}

fun NavGraphBuilder.contactNavGraph(navController: NavHostController) {
    navigation(
        route = "${Screen.UpdateContactPatient.route}/{slug}/contact",
        startDestination = Screen.UpdateContactPatient.route
    ) {
        composable(
            route = Screen.UpdateContactPatient.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientContactScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }
    }
}


//fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
//    navigation(
//        route = Screen.SignIn.route,
//        startDestination = Screen.SignIn.route
//    ) {
//        composable(
//            route = Screen.SignIn.route,
//        ) {
//            AuthenticationScreen(
//                navController,
//            )
//        }
//    }
//}



