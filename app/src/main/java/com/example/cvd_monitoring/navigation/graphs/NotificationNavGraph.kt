package com.example.cvd_monitoring.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cvd_monitoring.navigation.AuthRouteScreen
import com.example.cvd_monitoring.navigation.Graph
import com.example.cvd_monitoring.navigation.NotificationRouteScreen

fun NavGraphBuilder.notificationNavGraph(rootNavController: NavHostController){
    navigation(
        route = Graph.NotificationGraph,
        startDestination = NotificationRouteScreen.NotificationDetail.route
    ){
        composable(route = NotificationRouteScreen.NotificationDetail.route){

        }
    }
}