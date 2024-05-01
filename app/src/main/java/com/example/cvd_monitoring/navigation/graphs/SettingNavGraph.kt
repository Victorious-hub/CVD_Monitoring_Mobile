package com.example.cvd_monitoring.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.cvd_monitoring.navigation.Graph
import com.example.cvd_monitoring.navigation.NotificationRouteScreen
import com.example.cvd_monitoring.navigation.SettingRouteScreen
import com.example.cvd_monitoring.presentation.Screen
import com.ramcosta.composedestinations.annotation.RootNavGraph

fun NavGraphBuilder.settingNavGraph(rootNavController: NavHostController){
    navigation(
        route = Graph.SettingGraph,
        startDestination = SettingRouteScreen.SettingDetail.route
    ){
        composable(route = SettingRouteScreen.SettingDetail.route){
        }
    }
}