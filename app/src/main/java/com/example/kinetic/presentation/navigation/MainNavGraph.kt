package com.example.kinetic.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
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
        startDestination = Screens.HomeScreen.route
    ){
        composable(route = Screens.HomeScreen.route){
            HomeScreen(
                onThemeChange,
                navHostController = navHostController,
                onNavigate = { navHostController.navigate(it.route)}
            )
        }
        composable(route = Screens.GameDetailsScreen.route + "/{gameId}"){
            GameDetailsScreen(onPopBackStack = { navHostController.popBackStack() })
        }
        composable(route = Screens.SearchScreen.route){
            SearchScreen(navHostController = navHostController,
                onPopBackStack = { navHostController.popBackStack() })
        }
    }
}