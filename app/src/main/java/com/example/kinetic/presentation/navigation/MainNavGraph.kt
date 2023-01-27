package com.example.kinetic.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.kinetic.presentation.game_details.components.GameDetailsScreen
import com.example.kinetic.presentation.home.components.HomeScreen
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.search.components.SearchScreen
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
                when (initialState.destination.route) {
                    Screens.GameDetailsScreen.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000))
                    else -> null
                }
            },

            exitTransition = {
                when (targetState.destination.route) {
                    Screens.GameDetailsScreen.route ->
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
                when (initialState.destination.route) {
                    Screens.SearchScreen.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(1000))
                    Screens.HomeScreen.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000))
                    else -> null
                }
            },

            exitTransition = {
                when (targetState.destination.route) {
                    Screens.SearchScreen.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000))
                    Screens.HomeScreen.route ->
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
                when (initialState.destination.route) {
                    Screens.HomeScreen.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000))
                    Screens.GameDetailsScreen.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(1000))
                    else -> null
                }
            },

            exitTransition = {
                when (targetState.destination.route) {
                    Screens.HomeScreen.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(1000))
                    Screens.GameDetailsScreen.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000))
                    else -> null
                }
            }
        ){
            SearchScreen(navHostController = navHostController,
                onPopBackStack = { navHostController.popBackStack() })
        }
    }
}