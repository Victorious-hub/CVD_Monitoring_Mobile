package com.example.cvd_monitoring.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cvd_monitoring.presentation.authenticate_screen.SignInScreen
import com.example.cvd_monitoring.presentation.doctor_contact_screen.DoctorContactScreen
import com.example.cvd_monitoring.presentation.patient_card_screen.PatientCardScreen
import com.example.cvd_monitoring.presentation.patient_contact_screen.PatientContactScreen
import com.example.cvd_monitoring.presentation.patient_list.PatientListScreen
import com.example.cvd_monitoring.presentation.patient_navigation.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            //MainScreen()

            NavHost(
                navController = navController,
                startDestination = Screen.SignIn.route
            ) {
                composable(Screen.SignIn.route) {
                    SignInScreen(navController = navController)
                }
//
//                composable(Screen.SignUp.route) {
//                    SignUpScreen(navController=navController)
//                }
//
//                composable(Screen.SignIn.route) {
//                    SignInScreen(navController=navController)
//                }
//                composable(
//                    route = Screen.PatientList.route
//                ) {
//                    PatientListScreen(navController)
//                }
//                composable(
//                    route = "${Screen.PatientCard.route}/{slug}",
//                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
//                ) {
//                    PatientCardScreen(navController = navController)
//                }

//                composable(
//                    route = "${Screen.UpdateContactPatient.route}/{slug}/contact",
//                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
//                ) {
//                    PatientContactScreen(navController = navController)
//                }
                // }
            }
        }
    }
}

