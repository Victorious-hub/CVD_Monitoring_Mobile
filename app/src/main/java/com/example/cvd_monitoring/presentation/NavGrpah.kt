package com.example.cvd_monitoring.presentation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.auth.authentication_screen.AuthenticationScreen
import com.example.cvd_monitoring.presentation.navigation.home.HomeScreen
import com.example.cvd_monitoring.presentation.auth.register_screen.RegistrationScreen

import com.example.cvd_monitoring.presentation.check_user.CheckUserScreen
import com.example.cvd_monitoring.presentation.navigation.doctor_navigation.DoctorDrawer
import com.example.cvd_monitoring.presentation.navigation.doctor_navigation.DoctorNavigation
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientBottomNavItem
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientDrawer
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientNavigation
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen


//@RequiresApi(Build.VERSION_CODES.O)
//@SuppressLint("SuspiciousIndentation")
//@Composable
//fun NavGraph() {
//    val navController = rememberNavController()
//    NavHost(
//        navController = navController,
//        startDestination = Screen.Preview.route
//    ) {
//        composable(route = Screen.Preview.route) {
//            PreviewScreen(navController)
//        }
//        composable(route = Screen.Main.route) {
//            MainScreen()
//        }
//    }
//}
