package com.example.cvd_monitoring.presentation.navigation.patient_navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cvd_monitoring.navigation.AuthRouteScreen
import com.example.cvd_monitoring.navigation.Graph
import com.example.cvd_monitoring.presentation.Screen
import com.example.cvd_monitoring.presentation.auth.authentication_screen.AuthenticationScreen
import com.example.cvd_monitoring.presentation.doctors.doctor_profile_screen.DoctorProfileScreen
import com.example.cvd_monitoring.presentation.navigation.doctor_navigation.DoctorBottomNavItem
import com.example.cvd_monitoring.presentation.navigation.home.HomeScreen
import com.example.cvd_monitoring.presentation.navigation.more.LogoutViewModel
import com.example.cvd_monitoring.presentation.notification.NotificationScreen
import com.example.cvd_monitoring.presentation.overview.OverviewScreen
import com.example.cvd_monitoring.presentation.patients.doctor_list.DoctorListScreen
import com.example.cvd_monitoring.presentation.patients.patient_card.PatientCardScreen
import com.example.cvd_monitoring.presentation.patients.patient_contact_screen.PatientContactScreen
import com.example.cvd_monitoring.presentation.patients.patient_data_screen.PatientUpdateScreen
import com.example.cvd_monitoring.presentation.patients.patient_profile_screen.PatientProfileScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientDrawer(
    slug: String,
    viewModel: LogoutViewModel = hiltViewModel()
) {
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(modifier = Modifier
                    .background(Color(0xFFa5051f))
                    .fillMaxWidth()
                    .height(150.dp)) // Top green section
                Spacer(modifier = Modifier.height(16.dp)) // Add spacing below green section

                NavigationDrawerItem(
                    label = { Text(text = "Home") },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = Color.Black) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate("${Screen.CurrentPatient.route}/$slug/get") {
                            popUpTo(0)
                        }
                    }
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Card") },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "Card", tint = Color.Black) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate("${Screen.PatientCard.route}/$slug") {
                            popUpTo(0)
                        }
                    }
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Notifications") },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.Black) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate("${Screen.Notification.route}/$slug/patient") {
                            popUpTo(0)
                        }
                    }
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Logout") },
                    selected = false,
                    icon = { Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout", tint = Color.Black) },
                    onClick = {
                        coroutineScope.launch {
                            viewModel.logoutUser()
                            drawerState.close()
                        }
                        navigationController.navigate(AuthRouteScreen.SignIn.route)
                    }
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "DoctorList") },
                    selected = false,
                    icon = { Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = "DoctorList", tint = Color.Black) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate("${Screen.DoctorList.route}/$slug") {
                            popUpTo(0)
                        }
                    }
                )
                Divider()

            }
        }
    ) {
        Scaffold(
            topBar = {
                val coroutineScope = rememberCoroutineScope()
                TopAppBar(
                    title = { Text(text = "Welcome") },
                    backgroundColor = Color(0xFFa5051f),
                    contentColor = Color.White,
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Rounded.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) {
            NavHost(
                navController = navigationController,
                startDestination = Screen.OverView.route
            ) {
                composable(route = Screen.OverView.route) {
                    OverviewScreen(navigationController)
                }


                composable(route = AuthRouteScreen.SignIn.route) {
                    AuthenticationScreen(navigationController)
                }

                composable(
                    route = "${Screen.CurrentPatient.route}/{slug}/get",
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backstackEntry ->
                    PatientProfileScreen(
                        navigationController,
                        slug = backstackEntry.arguments?.getString("slug") ?: "",
                    )
                }

                composable(
                    route = "${Screen.DoctorList.route}/{slug}",
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backstackEntry ->
                    DoctorListScreen(
                        navigationController,
                        slug = backstackEntry.arguments?.getString("slug") ?: "",
                    )
                }

                composable(
                    route = "${Screen.CurrentDoctor.route}/{slug}",
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backstackEntry ->
                    DoctorProfileScreen(
                        navigationController,
                        slug = backstackEntry.arguments?.getString("slug") ?: "",
                    )
                }

                composable(
                    route = "${Screen.Notification.route}/{slug}/patient",
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backstackEntry ->
                    NotificationScreen(
                        navigationController,
                        slug = backstackEntry.arguments?.getString("slug") ?: "",
                    )
                }

                composable(
                    route = "${Screen.PatientCard.route}/{slug}",
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backstackEntry ->
                    PatientCardScreen(
                        navigationController,
                        slug = backstackEntry.arguments?.getString("slug") ?: "",
                    )
                }

                composable(
                    route = "${Screen.UpdateDataPatient.route}/{slug}/data",
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backstackEntry ->
                    PatientUpdateScreen(
                        navigationController,
                        slug = backstackEntry.arguments?.getString("slug") ?: "",
                    )
                }

                composable(
                    route = "${Screen.UpdateContactPatient.route}/{slug}/contact",
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backstackEntry ->
                    PatientContactScreen(
                        navigationController,
                        slug = backstackEntry.arguments?.getString("slug") ?: "",
                    )
                }
            }

        }
    }
}
