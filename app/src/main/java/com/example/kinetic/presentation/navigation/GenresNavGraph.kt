package com.example.kinetic.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.kinetic.presentation.games_list.components.GamesListScreen
import com.example.kinetic.presentation.screen.Screens
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.genresNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.GENRES,
        startDestination = Screens.GenreScreen.route
    ){
        composable(
            route = Screens.GenreScreen.route + "/{genre}",
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
            GamesListScreen(
                navHostController = navHostController,
                onPopBackStack = { navHostController.popBackStack() })
        }
    }
}