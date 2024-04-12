package com.example.cvd_monitoring.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cvd_monitoring.common.ConnectivityObserver
import com.example.cvd_monitoring.common.NetworkConnectivityObserver
import com.example.cvd_monitoring.data.remote.local.AuthPreferences
import com.example.cvd_monitoring.presentation.authenticate_screen.SignInScreen
import com.example.cvd_monitoring.presentation.patient_list.PatientListScreen
import com.example.cvd_monitoring.presentation.unavailable_connection.UnavailableConnectionScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        connectivityObserver.observe().onEach {
            println("Status is $it")
        }



        setContent {
            val status by connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserver.Status.Unavailable
            )
            val navController = rememberNavController()

            if (status == ConnectivityObserver.Status.Unavailable) {
                UnavailableConnectionScreen()
            } else {
                NavHost(
                    navController = navController,
                    startDestination = Screen.PatientList.route
                ) {
                    composable(Screen.PatientList.route) {
                        PatientListScreen(navController = navController)
                    }
//                    composable(Screen.PatientList.route) {
//                        PatientListScreen(navController=navController)
//                    }
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
}



