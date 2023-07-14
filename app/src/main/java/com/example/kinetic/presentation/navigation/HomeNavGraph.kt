package com.example.kinetic.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.kinetic.presentation.genres.components.GenresScreen
import com.example.kinetic.presentation.home.components.HomeScreen
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.search.components.SearchScreen
import com.example.kinetic.presentation.settings.components.SettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeNavGraph(
    onDarkOn: () -> Unit,
    onLightOn: () -> Unit,
    onFollowSystem: () -> Unit,
    onDynamicColorOn: () -> Unit,
    navHostController: NavHostController
){
    navigation(
        route = Graph.BOTTOM_BAR,
        startDestination = Screens.HomeScreen.route
    ){
        composable(
            route = Screens.GenreScreen.route,
            enterTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            initialOffset = {0},
                            animationSpec = tween(300)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(1000)
                        )
                    else -> null
                }
            }
        ){
            GenresScreen(
                onNavigate = { navHostController.navigate(it.route)}
            )
        }

        composable(
            route = Screens.HomeScreen.route,
            enterTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            initialOffset = {0},
                            animationSpec = tween(300)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(1000)
                        )
                    else -> null
                }
            }
        ){
            HomeScreen(
                navHostController = navHostController,
                onNavigate = { navHostController.navigate(it.route)}
            )
        }

        composable(
            route = Screens.SearchScreen.route,
            enterTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            initialOffset = {0},
                            animationSpec = tween(300)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(1000))
                    else -> null
                }
            }
        ){
            SearchScreen(navHostController = navHostController,
                onPopBackStack = { navHostController.popBackStack() })
        }

        composable(
            route = Screens.SettingsScreen.route,
            enterTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            initialOffset = {0},
                            animationSpec = tween(300)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(1000))
                    else -> null
                }
            }
        ){
            SettingsScreen(
                onLightOn,
                onDarkOn,
                onDynamicColorOn,
                onFollowSystem,
                onPopBackStack = { navHostController.popBackStack() }
            )
        }
    }
}