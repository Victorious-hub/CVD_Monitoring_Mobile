package com.example.cvd_monitoring.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cvd_monitoring.presentation.auth.authentication_screen.AuthenticationScreen
import com.example.cvd_monitoring.presentation.navigation.home.HomeScreen
import com.example.cvd_monitoring.presentation.auth.register_screen.RegistrationScreen
import com.example.cvd_monitoring.presentation.check_user.CheckUserScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.DoctorProfileScreen
import com.example.cvd_monitoring.presentation.navigation.doctor_navigation.DoctorNavigation
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientNavigation
import com.example.cvd_monitoring.presentation.patients.patient_data_screen.PatientUpdateScreen


@SuppressLint("SuspiciousIndentation")
@Composable
fun NavGraph() {
    val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.CheckUser.route ) {
        composable(route = Screen.CheckUser.route){
            CheckUserScreen(navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screen.SignIn.route){
            AuthenticationScreen(navController)
        }
        composable(route = Screen.SignUp.route){
            RegistrationScreen(navController)
        }

        composable(
            route = "${Screen.CurrentPatient.route}/{slug}/get",
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            PatientNavigation(
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }


        composable(
            route = "${Screen.CurrentDoctor.route}/{slug}/get",
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorNavigation(
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

    }


}