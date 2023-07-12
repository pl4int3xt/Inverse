package com.example.kinetic.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kinetic.presentation.navigation.MainNavGraph
import com.example.kinetic.presentation.screen.Screens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    onDarkOn: () -> Unit,
    onLightOn: () -> Unit,
    onFollowSystem: () -> Unit,
    onDynamicColorOn: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier.navigationBarsPadding(),
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = Screens.HomeScreen.route,
                        icon = Icons.Rounded.Home
                    ),
                    BottomNavItem(
                        name = "Search",
                        route = Screens.SearchScreen.route,
                        icon = Icons.Rounded.Search
                    ),
                    BottomNavItem(
                        name = "Settings",
                        route = Screens.SettingsScreen.route,
                        icon = Icons.Rounded.Settings
                    )
                ),
                navController = navController,
                onItemClick = { navController.navigate(it.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                } })
        }
    ) {
        MainNavGraph(
            navHostController = navController,
            onDarkOn = onDarkOn,
            onLightOn = onLightOn,
            onFollowSystem = onFollowSystem,
            onDynamicColorOn = onDynamicColorOn
        )
    }
}