package com.example.bottomnavbardemo.screens.home

import android.annotation.SuppressLint
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
import com.example.cvd_monitoring.presentation.bottom_navigation.DoctorBottomBar
import com.example.cvd_monitoring.presentation.bottom_navigation.getRouteWithSlug
import com.example.cvd_monitoring.presentation.bottom_navigation.graphs.doctor_graph.DoctorHomeNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DoctorBottomBarScreen(
    navController: NavHostController = rememberNavController(),
    slug: String
) {
    Scaffold(
        bottomBar = { DoctorBottomBar(navController = navController, slug) }
    ) {
        DoctorHomeNavGraph(navController = navController)
    }
}

@Composable
fun DoctorBottomBar(navController: NavHostController, slug: String) {
    val screens = listOf(
        DoctorBottomBar.Home,
        DoctorBottomBar.Profile,
        DoctorBottomBar.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
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
                is DoctorBottomBar.Home -> screen.getRouteWithSlug(slug)
                else -> screen.route
            }
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}