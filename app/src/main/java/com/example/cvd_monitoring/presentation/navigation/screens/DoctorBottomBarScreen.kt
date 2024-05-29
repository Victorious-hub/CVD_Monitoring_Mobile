package com.example.bottomnavbardemo.screens.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cvd_monitoring.presentation.navigation.DoctorBottomBar
import com.example.cvd_monitoring.presentation.navigation.getRouteWithSlug
import com.example.cvd_monitoring.presentation.navigation.graphs.doctor_graph.DoctorHomeNavGraph

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DoctorBottomBarScreen(
    navController: NavHostController = rememberNavController(),
    slug: String,
    logout: () -> Unit
) {
    Scaffold(
        bottomBar = { DoctorBottomBar(navController = navController, slug) }
    ) {
        DoctorHomeNavGraph(navController = navController, logout=logout)
    }
}

@Composable
fun DoctorBottomBar(navController: NavHostController, slug: String) {
    val screens = listOf(
        DoctorBottomBar.Home,
        DoctorBottomBar.Profile,
        DoctorBottomBar.Patients,
        DoctorBottomBar.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
            screens.forEach { screen ->
                AddItemDoctor(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                    slug = slug,
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItemDoctor(
    screen: DoctorBottomBar,
    currentDestination: NavDestination?,
    navController: NavHostController,
    slug: String
) {

    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            val route = when (screen) {
                is DoctorBottomBar.Profile -> screen.getRouteWithSlug(slug)
                is DoctorBottomBar.Patients -> screen.getRouteWithSlug(slug)
                else -> screen.route
            }
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}