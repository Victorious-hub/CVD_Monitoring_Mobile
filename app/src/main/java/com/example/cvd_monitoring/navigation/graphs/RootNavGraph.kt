package com.example.cvd_monitoring.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cvd_monitoring.navigation.Graph
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientDrawer
import com.example.cvd_monitoring.presentation.navigation.patient_navigation.PatientNavigation

@Composable
fun RootNavGraph(){
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.AuthGraph
    ) {
        authNavGraph(rootNavController = rootNavController)
        notificationNavGraph(rootNavController)
        settingNavGraph(rootNavController)
    }
}