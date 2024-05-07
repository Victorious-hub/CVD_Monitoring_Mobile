package com.example.cvd_monitoring.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.auth.authentication_screen.AuthenticationScreen
import com.example.cvd_monitoring.presentation.auth.register_screen.RegistrationScreen
import com.example.cvd_monitoring.presentation.check_user.CheckUserScreen
import com.example.cvd_monitoring.presentation.auth.HomeScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = Screen.CheckUser.route
    ) {
        composable(route = Screen.CheckUser.route) {
            CheckUserScreen(navController)
        }

        composable(route = AuthScreen.Home.route) {
            HomeScreen(navController)
        }

        composable(route = AuthScreen.Login.route) {
            AuthenticationScreen(navController)
        }

        composable(route = AuthScreen.SignUp.route) {
            RegistrationScreen(navController)
        }


    }
}

sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen(route = "LOGIN")
    data object SignUp : AuthScreen(route = "SIGN_UP")
    data object Home : AuthScreen(route = "HOME")
}