package com.example.kinetic.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.kinetic.presentation.game_details.components.GameDetailsScreen
import com.example.kinetic.presentation.screen.Screens
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.detailsNavGraph(navHostController: NavHostController){
    navigation(
        route = Graph.DETAILS + "/{gameId}",
        startDestination = Screens.GameDetailsScreen.route
    ){
        composable(
            route = Screens.GameDetailsScreen.route + "/{gameId}",
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
            GameDetailsScreen(
                onPopBackStack = { navHostController.popBackStack() },
                onNavigate = { navHostController.navigate(it.route) }
            )
        }
    }
}