package com.example.kinetic.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.kinetic.presentation.game_details.components.GameDetailsScreen
import com.example.kinetic.presentation.home.components.HomeScreen
import com.example.kinetic.presentation.main.MainScreen
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.search.components.SearchScreen
import com.example.kinetic.presentation.settings.components.SettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    onDarkOn: () -> Unit,
    onLightOn: () -> Unit,
    onFollowSystem: () -> Unit,
    onDynamicColorOn: () -> Unit
) {
    AnimatedNavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.BOTTOM_BAR
    ){
        detailsNavGraph(navHostController)

    }
}