package com.example.cvd_monitoring.presentation.patient_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cvd_monitoring.presentation.authenticate_screen.SignInScreen
import com.example.cvd_monitoring.presentation.patient_list.PatientListScreen
import com.example.cvd_monitoring.presentation.register_screen.SignUpScreen

//@Composable
//fun BottomNavGraph(navController: NavHostController) {
//    NavHost(
//        navController = navController,
//        startDestination = BottomBarScreen.Home.route
//    ) {
//        composable(route = BottomBarScreen.Home.route ) {
//            PatientListScreen(navController)
//        }
//
//        composable(route = BottomBarScreen.Settings.route) {
//            SignUpScreen(navController)
//        }
//    }
//}