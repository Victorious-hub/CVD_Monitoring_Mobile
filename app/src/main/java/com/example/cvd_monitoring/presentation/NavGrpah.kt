package com.example.cvd_monitoring.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cvd_monitoring.presentation.auth.signin_screen.SignInScreen
import com.example.cvd_monitoring.presentation.home.HomeScreen
import com.example.cvd_monitoring.presentation.auth.signup_screen.SignUpScreen
import com.example.cvd_monitoring.presentation.check_user.CheckUserScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.DoctorProfileScreen
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.CurrentUserScreen


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
            SignInScreen(navController)
        }
        composable(route = Screen.SignUp.route){
            SignUpScreen(navController)
        }

        composable(
            route = "${Screen.CurrentUser.route}/{slug}/get",
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            CurrentUserScreen(
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

        composable(
            route = "${Screen.CurrentDoctor.route}/{slug}/get",
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorProfileScreen(
                slug = backstackEntry.arguments?.getString("slug") ?: "",
            )
        }

    }


}