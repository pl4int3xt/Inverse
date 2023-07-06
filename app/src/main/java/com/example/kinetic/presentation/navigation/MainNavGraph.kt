package com.example.kinetic.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.kinetic.presentation.game_details.components.GameDetailsScreen
import com.example.kinetic.presentation.home.components.HomeScreen
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.search.components.SearchScreen
import com.example.kinetic.presentation.settings.components.SettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    onThemeChange: () -> Unit
) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route,
    ){
        composable(
            route = Screens.HomeScreen.route,
            enterTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
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
            HomeScreen(
                onThemeChange,
                navHostController = navHostController,
                onNavigate = { navHostController.navigate(it.route)}
            )
        }

        composable(
            route = Screens.GameDetailsScreen.route + "/{gameId}",
            enterTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
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
            GameDetailsScreen(
                onPopBackStack = { navHostController.popBackStack() },
                onNavigate = { navHostController.navigate(it.route) }
            )
        }

        composable(
            route = Screens.SearchScreen.route,
            enterTransition = {
                when (targetState.destination.route) {
                    navHostController.currentDestination?.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
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
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
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
            SettingsScreen()
        }
    }
}