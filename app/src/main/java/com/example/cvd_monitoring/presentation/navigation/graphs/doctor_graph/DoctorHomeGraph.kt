package com.example.cvd_monitoring.presentation.navigation.graphs.doctor_graph


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.domain.model.users.Doctor
import com.example.cvd_monitoring.presentation.auth.logout_screen.LogoutScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_patients.DoctorPatientsScreen
import com.example.cvd_monitoring.presentation.navigation.DoctorBottomBar
import com.example.cvd_monitoring.presentation.navigation.graphs.Graph
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.DoctorProfileScreen
import com.example.cvd_monitoring.presentation.navigation.PatientBottomBar
import com.example.cvd_monitoring.presentation.navigation.graphs.DataUpdateScreen
import com.example.cvd_monitoring.presentation.navigation.graphs.DoctorPatientActions
import com.example.cvd_monitoring.presentation.navigation.graphs.doctorPatientActionsNavGraph
import com.example.cvd_monitoring.presentation.navigation.graphs.getRouteWithSlug
import com.example.cvd_monitoring.presentation.navigation.graphs.updateDataNavGraph
import com.example.cvd_monitoring.presentation.overview.UserHomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DoctorHomeNavGraph(
    navController: NavHostController,
    logout: () -> Unit
) {
    NavHost(
        navController = navController,
        route = Graph.DOCTOR_HOME,
        startDestination = DoctorBottomBar.Home.route
    ) {

        composable(route = DoctorBottomBar.Home.route) {
            UserHomeScreen(
                navController,
            )
        }

        composable(
            route = DoctorBottomBar.Profile.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorProfileScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onUpdateContact = {
                    DataUpdateScreen.DoctorData.getRouteWithSlug(backstackEntry.arguments?.getString("slug"))
                        ?.let { it1 -> navController.navigate(it1) }
                },
            )
        }

        updateDataNavGraph(navController = navController)

        composable(
            route = DoctorBottomBar.Patients.route,
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backstackEntry ->
            DoctorPatientsScreen(
                navController,
                slug = backstackEntry.arguments?.getString("slug") ?: "",
                onUpdateContact = { email ->
                    val route = DoctorPatientActions.PatientProfile.getRouteWithSlug(email)
                    if (route != null) {
                        navController.navigate(route)
                    }
                },
                onClickBackToMain = {
                    navController.navigate(DoctorBottomBar.Home.route)
                }
            )
        }

        composable(route = PatientBottomBar.Settings.route) {
            LogoutScreen(navHostController = navController, logout = logout)
        }

        detailsNavGraph(
            navController = navController
        )
        doctorPatientActionsNavGraph(
            navController = navController
        )
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ROOT,
        startDestination = DetailsScreen.Overview.route
    ) {
//        composable(route = AuthScreen.Login.route) {
//            AuthenticationScreen(navController)
//        }

//        composable(
//            route = DetailsScreen.Overview.route,
//            arguments = listOf(navArgument("slug") { type = NavType.StringType })
//        ) { backstackEntry ->
//            PatientDetailScreen(
//                navController,
//                slug = backstackEntry.arguments?.getString("slug") ?: "",
//            )
//        }
    }
}

sealed class DetailsScreen(val route: String) {
    data object Information : DetailsScreen(route = "INFORMATION")
    data object Overview : DetailsScreen(route = "currentPatient/{slug}/get")
}